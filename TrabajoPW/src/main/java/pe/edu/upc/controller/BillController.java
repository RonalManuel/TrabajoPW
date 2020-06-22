package pe.edu.upc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Bill;
import pe.edu.upc.serviceinterface.IBillService;
import pe.edu.upc.serviceinterface.IPersonService;
import pe.edu.upc.serviceinterface.ITypeCardService;
import pe.edu.upc.serviceinterface.ITypeCurrencyService;
import pe.edu.upc.serviceinterface.ITypePaymentService;
import pe.edu.upc.serviceinterface.ISaleService;
import pe.edu.upc.serviceinterface.IRentService;



@Controller
@RequestMapping("/bills")
public class BillController {
	
	
	@Autowired
	private IBillService bS;
	@Autowired
	private ITypeCardService tS;
	@Autowired
	private ITypePaymentService paS;
	@Autowired
	private ITypeCurrencyService cuS;
	@Autowired
	private IPersonService pS;
	@Autowired
	private IRentService rS;
	@Autowired
	private ISaleService sS;
	
	@GetMapping("/new")
	public String newBill(Model model)
	{
		model.addAttribute("listTypeCard", tS.list());
		model.addAttribute("listTypeCurrency", cuS.list());
		model.addAttribute("listTypePayment", paS.list());
		model.addAttribute("listPersons", pS.list());
		model.addAttribute("listRents", rS.list());
		model.addAttribute("listSales", sS.list());
		model.addAttribute("bill",new Bill());
		return "bill/bill";
		
	};
	
	@PostMapping("/save")
	public String saveBill(@Validated Bill Bill, BindingResult result, Model model)
	{
		if(result.hasErrors())
			return "bill/bill";
		else {
			bS.insert(Bill);
			model.addAttribute("listBills", bS.list());
			return "redirect:/billS/list";
		}
		
	}
	
	@GetMapping("/list")
	public String listBill(Model model) {
		try {
			model.addAttribute("listBills",bS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "bill/listBills";
		
		
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Bill> objAr = bS.searchId(id);
		if (objAr == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/billS/list";
		} else {
			
			model.addAttribute("categoryProduct", objAr.get());
			model.addAttribute("mensaje", "Se Actualizo Correctamente");
			return "";
		}
	}



}