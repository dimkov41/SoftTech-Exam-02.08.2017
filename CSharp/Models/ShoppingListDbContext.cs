using System.Data.Entity;

namespace ShoppingList.Models
{
    public class ShoppingListDbContext : DbContext
    {
        public virtual DbSet<Product> Products { get; set; }

        public ShoppingListDbContext() : base("ShoppingList")
        {
        }
    }
}