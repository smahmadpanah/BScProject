package wlrewriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Mohammad
 */
public class AProvE {

    public int isTerminated = 0;

    public AProvE(String loop) {
        try {
            /*
             ////////////////////////////////
             //            Socket socket = new Socket(InetAddress.getByName("http://www.aprove.informatik.rwth-aachen.de/index.asp"), 80);
             //
             //            OutputStream os = socket.getOutputStream();
             //            boolean autoflush = true;
             //            PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
             //            BufferedReader in = new BufferedReader(
             //                    new InputStreamReader(socket.getInputStream()));
             //
             //            out.println("POST " + "/index.asp?subform=termination_proofs.html" + " HTTP/1.1");
             //            out.println("Host: " + "http://aprove.informatik.rwth-aachen.de" + ":80");
             //            out.println("Connection: Close");
             //            out.println();
             //            PrintStream ps = new PrintStream(socket.getOutputStream());
             //            loop = "#include <stdio.h> "
             //                   + "#define TRUE 1 "
             //                   + "#define true 1 "
             //                   + "#define FALSE 0 "
             //                   + "#define false 0 "
             //                   + "int main(){"
             //                   + "return 0; "
             //                   + "}";
             //
             //            String stringForPost = "program_type=c&source=" + loop + "&timeout=60&submitChangeInputType=Change&subform=termination_proofs.html&output=html&head=yes&submit_mode=Submit";
             //            out.print(stringForPost);
             //            out.close();
             //
             //            boolean loopa = true;
             //            StringBuilder sb = new StringBuilder(8096);
             //            while (loopa) {
             //                if (in.ready()) {
             //                    int i = 0;
             //                    while (i != -1) {
             //                        i = in.read();
             //                        sb.append((char) i);
             //                    }
             //                    loopa = false;
             //                }
             //            }
             // display the response to the out console
             //            String response = sb.toString();
             //      Scanner scanner = new Scanner(response);
             //            System.out.println("Total -> " + response + "END");
             ////////////////////////////////////*/

            URL url = new URL("http://aprove.informatik.rwth-aachen.de/index.asp?subform=termination_proofs.html");

            HttpURLConnection hConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);

            hConnection.setDoOutput(true);
            hConnection.setRequestMethod("POST");

            PrintStream ps = new PrintStream(hConnection.getOutputStream());

//            loop = "#include <stdio.h>\n"
//                   + "#define TRUE 1\n"
//                   + "#define true 1\n"
//                   + "#define FALSE 0\n"
//                   + "#define false 0\n"
//                   + "int main(){\n"
//                   + "return 0;\n"
//                   + "}\n";
//            loop = "int main(){return 0;}";
            String stringForPost = "program_type=c&source=" + loop + "&timeout=60&submitChangeInputType=Change&subform=termination_proofs.html&output=html&head=yes&submit_mode=Submit&sourcefile=1&query=1";
            ps.print(stringForPost);
//            ps.print("program_type=c&source=");
//            ps.println("#include <stdio.h>");
//            ps.println("#define TRUE 1");
//            ps.println("#define true 1");
//            ps.println("#define false 0");
//            ps.println("#define FALSE 0");
//            ps.println(loop);
//            ps.println("return 0;}");
//            ps.printf("&timeout=60&submitChangeInputType=Change&subform=termination_proofs.html&output=html&head=yes&submit_mode=Submit&sourcefile=1&query=1");

            ps.close();

            hConnection.connect();

            if (HttpURLConnection.HTTP_OK == hConnection.getResponseCode()) {
                System.out.println("Connection started.");
                GUI.terminal.append("Connection started.");
                InputStream is = hConnection.getInputStream();
//                OutputStream os = new FileOutputStream("output.html");
                String os = "";
                int data;

                while ((data = is.read()) != -1) {
                    os += (char) data;
                }
                is.close();
//                System.out.println(os);
                if (os.contains("Termination</b> of the given <i>C Problem</i> could be <font color=\"#00cc00\">proven")) {
                    System.out.println("PROVEN");
                    isTerminated = 1;
                }

                else {
                    if (os.contains("Termination</b> of the given <i>C Problem</i> could be <font color=\"#cc0000\">disproven")) {
                        System.out.println("DISPROVEN");
                        isTerminated = 0;
                    }

                    else {
                        System.out.println("MAYBE");
                        isTerminated = 2;
                    }

                }

                hConnection.disconnect();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Connection Failed.");
            GUI.terminal.appendError("Connection Failed.");
        }
    }
}
