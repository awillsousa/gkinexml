
package gkinexml;

import java.io.*;
import javax.swing.JTextArea;

/**
 * Cria um fluxo de saída customizado.
 * @author anton_000
 */

public class CustomOutputStream extends OutputStream {

private JTextArea textArea;

public CustomOutputStream(JTextArea ta) throws IOException {
    this.textArea = ta;   
}

@Override
public void write(int i) throws IOException {
    // Redireciona para o textArea
    textArea.append(String.valueOf((char)i));
    // Vai para o fim do texto 
    textArea.setCaretPosition(textArea.getDocument().getLength());
}
}