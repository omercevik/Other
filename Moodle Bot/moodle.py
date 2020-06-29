import smtplib
import ssl
import time
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

from selenium import webdriver
from selenium.webdriver.firefox.options import Options


class Moodle:
    def __init__(self):
        options = Options()
        options.add_argument('--headless')
        self.browser = webdriver.Firefox(executable_path=r'GECKODRIVER_PATH', options=options)
        self.username = ""
        self.password = ""
        self.send_email = ""
        self.receiver_email = ""
        self.mailPassword = ""
        self.readFile()

    def signIn(self):
        self.browser.get("https://bilmuh.gtu.edu.tr/moodle/login/index.php")
        time.sleep(2)

        self.browser.find_element_by_xpath("//*[@id='username']").send_keys(self.username)
        self.browser.find_element_by_xpath("//*[@id='password']").send_keys(self.password)

        time.sleep(1)
        self.browser.find_element_by_xpath("//*[@id='loginbtn']").click()
        self.sendMail(["Signed In!"])

    def getOnlines(self):
        list = self.browser.find_element_by_css_selector("ul.list:nth-child(2)").text
        onlineNames = ["Erchan Aptoula", "Yusuf Sinan Akgül"]
        mailList = []
        for name in onlineNames:
            if name in list:
                mailList.append(name)

        if mailList:
            self.sendMail(mailList)
            time.sleep(500)
        else:
            time.sleep(15)
        self.browser.refresh()


    def sendMail(self, names):
        port = 587  # For starttls
        smtp_server = "smtp.live.com"

        subject = "Moodle Onlines"
        body = "Hello Sir!\n\n"
        for name in names:
            body += name + " is online in Moodle right now!\n"
        body += "\n"
        body += datetime.now().strftime("%B %d, %Y  %H:%M:%S")
        message = MIMEMultipart()
        message["From"] = self.send_email
        message["To"] = self.receiver_email
        message["Subject"] = subject
        message.attach(MIMEText(body, "plain"))

        context = ssl.create_default_context()
        with smtplib.SMTP(smtp_server, port) as server:
            server.ehlo()  # Can be omitted
            server.starttls(context=context)
            server.ehlo()  # Can be omitted
            server.login(self.send_email, self.mailPassword)
            server.sendmail(self.send_email, self.receiver_email, message.as_string())

    def readFile(self):
        file = open("USERINFO.TXT_PATH","r")
        self.username = file.readline().strip('\n')
        self.password = file.readline().strip('\n')
        self.send_email = file.readline().strip('\n')
        self.mailPassword = file.readline().strip('\n')
        self.receiver_email = file.readline().strip('\n')
        file.close()



moodle = Moodle()
moodle.signIn()
while True:
    moodle.getOnlines()
