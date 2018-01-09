package com.abc.springMVC;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Added later
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.abc.dao.CarDao;
import com.abc.model.Car;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@Autowired
	CarDao dao; // Will inject DAO from xml bean file
	
	/**
	 * Displays a form to input data. "Command" is a reserved req attribute which 
	 * is used to display object data into the form.
	 */
	@RequestMapping("/addCars")
	public ModelAndView showform() {
		return new ModelAndView("addCars","command", new Car());
	}
	
	/**
	 * Saves obj into database. The @ModelAttribute puts request data into the model object,
	 * you need to mention RequestMethod. POST because the default request is GET
	 */
	@RequestMapping(value="/addCars", method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute(" ") Car car) {
		dao.addCar(car);
		return new ModelAndView("redirect:/viewCars"); // will redirect to viewCar req mapping
	}
	
	/**
	 * Provides list of cars in model object
	 */
	@RequestMapping(value="/viewCars")
	public ModelAndView viewCars() {
		List<Car> list = dao.getAllCars();
		return new ModelAndView("viewCars", "list", list);
	}
	
	/**
	 * Displays obj data into form for the given carID
	 * The @PathVariable puts URL data into the variable
	 */
	@RequestMapping(value="/editCars/{carId}")
	public ModelAndView edit(@PathVariable int carId) {
		Car car = dao.getCarById(carId);
		return new ModelAndView("editCars","command",car);
	}
	
	//updates model object
	@RequestMapping(value="/editAndsave", method= RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("car") Car car) {
		dao.updateCar(car);
		return new ModelAndView("redirect:/viewCars");
	}
	
	//deletes model object
		@RequestMapping(value="/deleteCars/{carId}", method= RequestMethod.GET)
		public ModelAndView delete(@PathVariable int carId) {
			dao.deleteCar(carId);
			return new ModelAndView("redirect:/viewCars");
		}
}
