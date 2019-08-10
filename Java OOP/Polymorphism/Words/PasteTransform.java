package Polymorphism.Words;

public class PasteTransform implements TextTransform {
    private CutTransform pasteBuffer;
    public PasteTransform(CutTransform pasteBuffer) {
        this.pasteBuffer = pasteBuffer;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        text.replace(startIndex, endIndex, this.pasteBuffer.getLastCut().toString());
    }
}
