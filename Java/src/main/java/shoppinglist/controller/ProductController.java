package shoppinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shoppinglist.bindingModel.ProductBindingModel;
import shoppinglist.entity.Product;
import shoppinglist.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {

	private final ProductRepository productRepository;

	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);
		model.addAttribute("view", "product/index");
		return "base-layout";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("view", "product/create");
		return "base-layout";
	}

	@PostMapping("/create")
	public String createProcess(Model model, ProductBindingModel productBindingModel, BindingResult bindingResult) {
		if(productBindingModel.getName().equals("")){
			model.addAttribute("product", productBindingModel);
			model.addAttribute("view", "product/create");
			return "base-layout";
		}
		Product currentProduct = new Product();
		currentProduct.setName(productBindingModel.getName());
		currentProduct.setPriority(productBindingModel.getPriority());
		currentProduct.setQuantity(productBindingModel.getQuantity());
		currentProduct.setStatus("not bought");
		productRepository.saveAndFlush(currentProduct);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Integer id) {
		if(id != null){
			Product currentProduct = productRepository.findOne(id);
			model.addAttribute("product", currentProduct);
			model.addAttribute("view", "product/edit");
			return "base-layout";
		}
		return "redirect:/";
	}

	@PostMapping("/edit/{id}")
	public String editProcess(Model model, @PathVariable Integer id, ProductBindingModel productBindingModel) {
		if(!productBindingModel.getName().equals("") && id != null){
			Product currentProduct = productRepository.findOne(id);
			currentProduct.setName(productBindingModel.getName());
			currentProduct.setStatus(productBindingModel.getStatus());
			currentProduct.setQuantity(productBindingModel.getQuantity());
			currentProduct.setPriority(productBindingModel.getPriority());
			productRepository.saveAndFlush(currentProduct);
			return "redirect:/";
		}

		Product currProduct = productRepository.findOne(id);
		model.addAttribute("product", currProduct);
		model.addAttribute("view", "product/edit");
		return "base-layout";
	}
}
