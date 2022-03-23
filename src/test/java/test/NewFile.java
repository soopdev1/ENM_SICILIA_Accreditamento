/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import static it.refill.action.ActionB.preparefilefordownload;
import static it.refill.action.ActionB.preparefileforupload;
import static it.refill.action.Pdf_new.allegatoC;
import static it.refill.action.Pdf_new.nullaosta;
import it.refill.db.Db_Bando;
import it.refill.entity.Docuserconvenzioni;
import it.refill.entity.FileDownload;
import java.io.File;
import java.util.ArrayList;
import org.joda.time.DateTime;

/**
 *
 * @author Administrator
 */
public class NewFile {

    public static void main(String[] args) {
//        File out1 = allegatoC("FVALOC6567");
//        System.out.println(out1.getPath());

        String username = "DMARRE0776";
        Db_Bando db = new Db_Bando();
        ArrayList<Docuserconvenzioni> aldocumenti = db.getDocumentiConvenzioni(username);
        String protocollo = db.getProtocolloDomandaInviata(username).toUpperCase();
        String ragsoc = db.getRagioneSociale(username).toUpperCase();
        db.closeDB();
        Docuserconvenzioni conv = aldocumenti.stream().filter(d1 -> d1.getCodicedoc().contains("CONV")).findAny().orElse(null);
        if (conv != null) {
            File nullaosta = nullaosta(username, protocollo, ragsoc, new DateTime());
            System.out.println(nullaosta.getPath());
        }
        
    }
}
