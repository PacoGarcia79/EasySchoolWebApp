package com.eazybytes.eazyschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;

import lombok.extern.slf4j.Slf4j;

//import jakarta.validation.Valid;  //EN VERSIÓN 3.0.0. DEL PARENT
import javax.validation.Valid;

@Slf4j
@Controller
public class ContactController {

	// private static Logger log = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;

	@RequestMapping("/contact")
	public String displayContactPage(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact.html";
	}

//	@PostMapping(value = "/saveMsg")
//	public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
//			@RequestParam String email, @RequestParam String subject, @RequestParam String message) {
//		log.info("Name : " + name);
//		log.info("Mobile Number : " + mobileNum);
//		log.info("Email Address : " + email);
//		log.info("Subject : " + subject);
//		log.info("Message : " + message);
//		return new ModelAndView("redirect:/contact");
//	}

	@PostMapping(value = "/saveMsg")
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {

		log.info("Name : " + contact.getName());
		log.info("Mobile Number : " + contact.getMobileNum());
		log.info("Email Address : " + contact.getEmail());
		log.info("Subject : " + contact.getSubject());
		log.info("Message : " + contact.getMessage());

		if (errors.hasErrors()) {
			log.error("Contact form validation failed due to : " + errors.toString());
			return "contact.html";
		}

		contactService.saveMessageDetails(contact);
        contactService.setCounter(contactService.getCounter()+1);
        log.info("Number of times the Contact form is submitted : "+contactService.getCounter());
        return "redirect:/contact";
	}
}
