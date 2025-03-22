package Utilities;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SendEmail {
    public static void sendEmail(String senderEmail, String senderPassword, List<String> recipientEmails, String subject, List<TestCase> body) {
        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with Gmail server
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            int index = 1;
            int passCount = 0; // Initialize counters for pass and fail
            int failCount = 0;

            // Set From: header field of the header
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field of the header
            String recipients = String.join(",", recipientEmails);

            // Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));

            // Set Subject: header field
            message.setSubject(subject);

            StringBuilder htmlBody = new StringBuilder();
            htmlBody.append("<html><body>");
            htmlBody.append("Dear Team,<br>I hope this message finds you well<br>Attached is the Test Cases Report for Kim Loong Web Test Server. This report provides an overview of the test cases executed, including their status, test steps, and the overall success rate");
            htmlBody.append("<table border=\"1\" style='border-collapse: collapse;'>");
            htmlBody.append("<tr><th style='width: 200px; border: 1px solid black;'>No</th><th style='width: 200px; border: 1px solid black;'>Test Scenario</th><th style='width: 200px; border: 1px solid black;'>Test Case Name & Test Step</th><th style='width: 100px; border: 1px solid black;'>Expected Result</th><th style='width: 100px; border: 1px solid black;'>Status</th></tr>");


            for (TestCase testCase : body) {
                htmlBody.append("<tr>");
                htmlBody.append("<td>").append(index).append("</td>");
                htmlBody.append("<td>").append(testCase.getModul()).append("</td>");
                htmlBody.append("<td>").append(testCase.getName()).append("<br>").append(testCase.getFormattedSteps()).append("</td>");
                htmlBody.append("<td>").append(Helper.removeShould(testCase.getName())).append("</td>");
                htmlBody.append("<td>").append(testCase.getStatus()).append("</td>");
                htmlBody.append("</tr>");

                if ("PASS".equalsIgnoreCase(testCase.getStatus())) {
                    passCount++;
                } else{
                    failCount++;
                }


                index++;
            }


            htmlBody.append("</table>");

            int totalCases = passCount + failCount;
            double successRate = totalCases > 0 ? (passCount / (double) totalCases) * 100 : 0;

            // Append the summary and success rate to the HTML
            htmlBody.append("<br><h3>Summary:</h3>");
            htmlBody.append("<p>Total Passed: ").append(passCount).append("</p>");
            htmlBody.append("<p>Total Failed: ").append(failCount).append("</p>");
            htmlBody.append("<p>Success Rate: ").append(String.format("%.2f", successRate)).append("%</p>");

            htmlBody.append("</body></html>");

            // Now set the actual message
            message.setContent(htmlBody.toString(), "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
