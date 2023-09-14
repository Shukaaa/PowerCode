package rip.shukaaa.handler;

import com.spire.presentation.IAutoShape;
import com.spire.presentation.ISlide;
import com.spire.presentation.ParagraphEx;
import com.spire.presentation.Presentation;

import java.util.ArrayList;
import java.util.HashMap;

public class PowerpointHandler {
    private final Presentation presentation;

    public PowerpointHandler(String path) throws Exception {

        Presentation presentation = new Presentation();
        presentation.loadFromFile(path);
        this.presentation = presentation;
    }

    public HashMap<String, ArrayList<String>> getSlideMap() {
        HashMap<String, ArrayList<String>> slideMap = new HashMap<>();

        for (Object slide : presentation.getSlides()) {
            String title = ((ISlide) slide).getTitle();
            slideMap.put(title, new ArrayList<>());
            int index = 0;
            for (Object shape : ((ISlide) slide).getShapes()) {
                // Skip the first shape as it is the title
                if (index == 0) {
                    index++;
                    continue;
                }

                if (shape instanceof IAutoShape) {
                    for (Object tp : ((IAutoShape) shape).getTextFrame().getParagraphs()) {
                        ParagraphEx paragraph = (ParagraphEx) tp;
                        slideMap.get(title).add(paragraph.getText());
                    }
                }
            }
        }

        return slideMap;
    }

    public void dispose() {
        this.presentation.dispose();
    }
}
