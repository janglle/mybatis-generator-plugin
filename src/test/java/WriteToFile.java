import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.io.*;
import java.util.List;

/**
 * @author jangle at 2018/1/30 15:28
 */
public class WriteToFile {
    public void write(XmlElement element) {
        List<Element> elements = element.getElements();
        StringBuilder sb = new StringBuilder();
        for (Element element1 : elements) {
            sb.append(element1.getClass().getName());
            if (element1 instanceof TextElement) {
                TextElement e = (TextElement) element1;
                sb.append(" ").append(e.getContent()).append("\n");
            }
        }
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(new File("C:/var/a.txt")));
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String s) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(new File("C:/var/a.txt")));
            writer.write(s);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
