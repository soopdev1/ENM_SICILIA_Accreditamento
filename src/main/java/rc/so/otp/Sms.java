/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.so.otp;

import com.mailjet.client.ClientOptions;
import static com.mailjet.client.ClientOptions.builder;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.sms.SmsSend;
import static rc.so.action.ActionB.trackingAction;
import rc.so.db.Db_Bando;
import static rc.so.util.Utility.estraiEccezione;

/**
 *
 * @author rcosco
 */
public class Sms {

    public static boolean sendSMS2022(String cell, String msg) {
        try {
            Db_Bando dbb = new Db_Bando();
            String mailjet_smstoken = dbb.getPath("mailjet_smstoken");
            String mailjet_smsname = dbb.getPath("mailjet_smsname");
            dbb.closeDB();

            ClientOptions options = builder().bearerAccessToken(mailjet_smstoken).build();
            MailjetClient client = new MailjetClient(options);
            MailjetRequest request = new MailjetRequest(SmsSend.resource)
                    .property(SmsSend.FROM, mailjet_smsname)
                    .property(SmsSend.TO, "+39" + cell)
                    .property(SmsSend.TEXT, msg);
            MailjetResponse response = client.post(request);
            if (response.getStatus() == 200) {
                return true;
            }
            trackingAction("ERROR SYSTEM", "sendSMS2022: " + response.getStatus() + " - " + response.toString());
        } catch (Exception e) {
            trackingAction("ERROR SYSTEM", estraiEccezione(e));
        }
        return false;
    }
}