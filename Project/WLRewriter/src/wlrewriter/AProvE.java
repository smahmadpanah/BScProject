package wlrewriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Mohammad
 */
public class AProvE {

    public boolean isTerminated = false;

    public AProvE(String loop) {
        try {
            URL url = new URL("http://aprove.informatik.rwth-aachen.de/index.asp?subform=termination_proofs.html");

            HttpURLConnection hConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);

            hConnection.setDoOutput(true);
            hConnection.setRequestMethod("POST");

            PrintStream ps = new PrintStream(hConnection.getOutputStream());

            ps.print("program_type=c&source=" + loop + "&timeout=100&submitChangeInputType=Change&subform=termination_proofs.html&output=html&head=yes&submit_mode=Submit");
            ps.close();

            hConnection.connect();

            if (HttpURLConnection.HTTP_OK == hConnection.getResponseCode()) {
                System.out.println("Connection started.");
                InputStream is = hConnection.getInputStream();
//                OutputStream os = new FileOutputStream("output.html");
                String os = "";
                int data;

                while ((data = is.read()) != -1) {
                    os += (char) data;
                }
                is.close();
                if (os.contains("Termination</b> of the given <i>C Problem</i> could be <font color=\"#00cc00\">proven")) {
                    System.out.println("PROVEN");
                    isTerminated = true;
                }

                else {
                    if (os.contains("Termination</b> of the given <i>C Problem</i> could be <font color=\"#cc0000\">disproven")) {
                        System.out.println("DISPROVEN");
                    }

                    else {
                        System.out.println("MAYBE");
                    }

                    isTerminated = false;
                }

                hConnection.disconnect();
            }
        } catch (Exception ex) {
            System.err.println("Connection Failed.");
//            ex.printStackTrace();
        }
    }
}
