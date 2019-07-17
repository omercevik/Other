using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Web.Mvc;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            MedicineViewModel.showFlag = true;
            return View();
        }

        public ActionResult Index2()
        {
            MedicineViewModel.showFlag = !MedicineViewModel.showFlag;

            SaglikDBEntities1 db = new SaglikDBEntities1();
            List<MedicineTable> list = db.MedicineTables.ToList();

            List<MedicineViewModel> mvModelList = list.Select(x => new MedicineViewModel
            {
                MedicineName = x.MedicineName,
                MedicineDate = x.MedicineDate,
                MedicineId = x.MedicineId,
                MedicineCount = x.MedicineCount,
                MedicineUnit = x.MedicineUnit,
                IsDeleted = x.IsDeleted
            }).ToList();

            if (mvModelList == null)
                return View();

            return View(mvModelList);
        }

        public ActionResult Index3()
        {
            SaglikDBEntities1 db = new SaglikDBEntities1();
            List<MedicineTable> list = db.MedicineTables.ToList();
            ViewBag.MedicineList = list;
            return View();
        }

        public ActionResult Index4()
        {
            SaglikDBEntities1 db = new SaglikDBEntities1();
            List<MedicineTable> list = db.MedicineTables.ToList();
            ViewBag.MedicineList = list;
            return View();
        }

        public ActionResult deleteData(int id)
        {
            using (SaglikDBEntities1 db = new SaglikDBEntities1())
            {
                return View(db.MedicineTables.Where(x => x.MedicineId == id).FirstOrDefault());
            }
        }

        [HttpPost]
        public ActionResult deleteData(int id, FormCollection collection)
        {
            try
            {
                using (SaglikDBEntities1 db = new SaglikDBEntities1())
                {
                    MedicineTable deletedMedicine = db.MedicineTables.Where(x => x.MedicineId == id).FirstOrDefault();
                    db.Entry(deletedMedicine).CurrentValues.SetValues(
                        new MedicineTable
                        {
                            IsDeleted = true, MedicineCount = deletedMedicine.MedicineCount,
                            MedicineDate = deletedMedicine.MedicineDate, MedicineId = deletedMedicine.MedicineId,
                            MedicineName = deletedMedicine.MedicineName, MedicineUnit = deletedMedicine.MedicineUnit
                        });
                    db.SaveChanges();
                }
                MedicineViewModel.showFlag = true;
                return RedirectToAction("Index2");
            }
            catch
            {
                return View();
            }
        }

        public ActionResult SaveRecord()
        {
            return View();
        }

        [HttpPost]
        public ActionResult SaveRecord(MedicineViewModel model)
        {
            try
            {
                SaglikDBEntities1 db = new SaglikDBEntities1();

                //SendEmail("", "İlaçların Tarihlerinin Geçmesine 1 Aydan Az Kaldı", DateList.ToString());
                MedicineTable newMedicine = new MedicineTable
                {
                    IsDeleted = model.IsDeleted,
                    MedicineCount = model.MedicineCount,
                    MedicineDate = model.MedicineDate,
                    MedicineId = db.MedicineTables.ToList().Count,
                    MedicineName = model.MedicineName,
                    MedicineUnit = model.MedicineUnit
                };
                db.MedicineTables.Add(newMedicine);
                db.SaveChanges();
                MedicineViewModel.showFlag = true;
                return RedirectToAction("Index4");
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public JsonResult SendEmail(string toEmail, string subject, string emailBody)
        {
            try
            {
                string senderEmail = System.Configuration.ConfigurationManager.AppSettings["SenderEmail"].ToString();
                string senderPassword = System.Configuration.ConfigurationManager.AppSettings["SenderPassword"].ToString();

                SmtpClient client = new SmtpClient("smtp.gmail.com", 587);
                client.EnableSsl = true;
                client.Timeout = 100000;
                client.DeliveryMethod = SmtpDeliveryMethod.Network;
                client.UseDefaultCredentials = false;
                client.Credentials = new NetworkCredential(senderEmail, senderPassword);

                MailMessage mailMessage = new MailMessage(senderEmail, toEmail, subject, emailBody);
                mailMessage.IsBodyHtml = true;
                mailMessage.BodyEncoding = System.Text.UTF8Encoding.UTF8;
                client.Send(mailMessage);
                return Json(true,JsonRequestBehavior.AllowGet);
            }
            catch(Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
    }
}