namespace WebApplication1.Models
{
    public class MedicineViewModel
    {
        public static bool showFlag { get; set; }
        public int MedicineId { get; set; }
        public string MedicineName { get; set; }
        public string MedicineUnit { get; set; }
        public int MedicineCount { get; set; }
        public bool IsDeleted { get; set; }
        public System.DateTime MedicineDate { get; set; }
    }
}