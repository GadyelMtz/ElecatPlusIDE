package elecatpluside;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class TextPaneColorizer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Editor de Código");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JTextPane textPane = new JTextPane();
        frame.add(new JScrollPane(textPane));

        frame.setVisible(true);

        // Agregar un StyledDocument al JTextPane
        StyledDocument doc = textPane.getStyledDocument();

        // Agregar estilos para resaltar las variables
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        Style variableStyle = doc.addStyle("VariableStyle", defaultStyle);
        StyleConstants.setForeground(variableStyle, Color.BLUE);

        // Agregar un DocumentListener para actualizar los estilos
        doc.addDocumentListener(new DocumentListener() {


            @Override
            public void insertUpdate(DocumentEvent e) {
                 updateStyles(doc);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 updateStyles(doc);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                 updateStyles(doc);
            }
        });

        // Texto de ejemplo
        String exampleText = "Este es un ejemplo de #Variable_14# en un JTextPane.";
        textPane.setText(exampleText);

        // Actualizar los estilos al inicio
        updateStyles(doc);
    }

   // Método para aplicar estilos a las variables
private static void updateStyles(StyledDocument doc) {
    Style variableStyle = doc.getStyle("VariableStyle");
    String text;
    try {
        text = doc.getText(0, doc.getLength());
    } catch (BadLocationException e) {
        return;
    }

    Pattern pattern = Pattern.compile("#[a-zA-Z][a-zA-Z0-9_]*#");
    Matcher matcher = pattern.matcher(text);

    while (matcher.find()) {
        int start = matcher.start();
        int end = matcher.end();

        // Usar SwingUtilities.invokeLater() para realizar la modificación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            doc.setParagraphAttributes(start, end - start, variableStyle, false);
        });
    }
}

}
