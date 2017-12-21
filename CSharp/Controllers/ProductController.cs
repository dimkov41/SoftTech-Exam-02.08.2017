using System.Linq;
using System.Web.Mvc;
using ShoppingList.Models;

namespace ShoppingList.Controllers
{
    [ValidateInput(false)]
    public class ProductController : Controller
    {
        ShoppingListDbContext db = new ShoppingListDbContext();

        [HttpGet]
        [Route("")]
        public ActionResult Index()
        {
            var allProducts = db.Products.ToList();
            return View(allProducts);
        }

        [HttpGet]
        [Route("create")]
        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        [Route("create")]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Product product)
        {
            if (this.ModelState.IsValid)
            {
                Product currentProduct = new Product();
                currentProduct.Name = product.Name;
                currentProduct.Priority = product.Priority;
                currentProduct.Quantity = product.Quantity;
                currentProduct.Status = "not bought";
                db.Products.Add(currentProduct);
                db.SaveChanges();

                return Redirect("/");
            }

            return View();
        }

        [HttpGet]
        [Route("edit/{id}")]
        public ActionResult Edit(int? id)
        {
            if(id != null)
            {
                Product currentProduct = db.Products.Find(id);
                return View(currentProduct);
            }

            return Redirect("/");
        }

        [HttpPost]
        [Route("edit/{id}")]
        [ValidateAntiForgeryToken]
        public ActionResult EditConfirm(int? id, Product productModel)
        {
            if(id != null || this.ModelState.IsValid)
            {
                Product currentProduct = db.Products.Find(id);
                currentProduct.Name = productModel.Name;
                currentProduct.Priority = productModel.Priority;
                currentProduct.Quantity = productModel.Quantity;
                currentProduct.Status = productModel.Status;
                db.SaveChanges();

                return Redirect("/");

            }

            Product product = db.Products.Find(id);
            return View(product);
        }
    }
}