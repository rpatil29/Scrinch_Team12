/*
Scrinch is a stand-alone Swing application that helps managing your
projects based on Agile principles.
Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluekiger,
Christian Lebaudy, Jean-Marc Borer

Scrinch is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Scrinch is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.scrinch.gui;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import org.scrinch.Main;
import org.scrinch.model.Item;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class ItemSetPdfExporter {

    private static BaseFont HANDWRITTEN_FONT1_PDF;
    private static String FONT_PATH = "journal.ttf";
    private ItemTablePanel itemTablePanel;
    private static final Rectangle A4_LANDSCAPE = PageSize.A4.rotate();

    private void initPdfFonts() {
        if (HANDWRITTEN_FONT1_PDF == null) {
            try {
                FileOutputStream fout = new FileOutputStream(FONT_PATH);
                InputStream is = getClass().getResourceAsStream(ItemPostIt.HANDWRITTEN_FONT1_PATH);
                int b = -1;
                while ((b = is.read()) != -1) {
                    fout.write(b);
                }
                fout.close();
                is.close();
                HANDWRITTEN_FONT1_PDF = BaseFont.createFont(FONT_PATH, BaseFont.CP1252, BaseFont.EMBEDDED);
                //don't try to delete the temporary font file here: the defaultfontmapper still need it
            } catch (Exception e) {
                ScrinchEnvToolkit.logger.log(Level.WARNING, "Could not write font file", e);
            }
        }
    }

    public ItemSetPdfExporter(ItemTablePanel panel) {
        this.itemTablePanel = panel;
    }
    
    protected Sprint getSprint(){
        return null;
    }

    public void exportSetToPdfFile() throws FileNotFoundException, DocumentException, IOException {
        initPdfFonts();
        String lastExportDir = Main.getUserProperty(Main.LAST_EXPORT_DIR);
        JFileChooser fileChooser = new JFileChooser(lastExportDir);
        File defaultFile = new File(lastExportDir
                + File.separator + itemTablePanel.getItemSet().getTitle().replace(' ', '_')
                + ".pdf");
        fileChooser.setSelectedFile(defaultFile);
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(itemTablePanel)) {
            File file = fileChooser.getSelectedFile();
            Main.setUserProperty(Main.LAST_EXPORT_DIR, file.getParent());
            Rectangle pageRect = A4_LANDSCAPE;
            int leftMargin = 20;
            int rightMargin = 10;
            int topMargin = 10;
            int bottomMargin = 5;
            com.lowagie.text.Document iTextDocument =
                    new com.lowagie.text.Document(pageRect, leftMargin, rightMargin, topMargin, bottomMargin);
            PdfWriter writer = PdfWriter.getInstance(iTextDocument,
                    new FileOutputStream(fileChooser.getSelectedFile()));
            iTextDocument.open();

            addTitle(iTextDocument);
            addItemPages(iTextDocument, writer, leftMargin, rightMargin, topMargin, bottomMargin);
            iTextDocument.close();
            org.scrinch.ostools.Runtime.pdfOpen(fileChooser.getSelectedFile().getPath());
        }
    }

    private void addItemPages(com.lowagie.text.Document iTextDocument,
            PdfWriter writer,
            int leftMargin, int rightMargin, int topMargin, int bottomMargin) throws DocumentException, IOException {

        PdfContentByte cb = writer.getDirectContent();

        float width = A4_LANDSCAPE.getWidth();
        float height = A4_LANDSCAPE.getHeight();

        Item[] items = (Item[]) itemTablePanel.getItemSet().getItemList().toArray(new Item[0]);
        List<ItemPostIt> itemPostItList = new ArrayList<ItemPostIt>();
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (getItemPrintValidity(item)) {
                ItemPostIt itemPostIt = new ItemPostIt(item, Item.Status.STANDBY.getColorCode());
                itemPostIt.setSprint(getSprint());
                itemPostItList.add(itemPostIt);
            }
        }

        int printed = 25; //to let some space for the title
        int itemMargin = 4;
        int lastItemIndex = 0;
        int lastPostItIndex = 0;
        while (lastItemIndex < items.length) {

            PdfTemplate tp = cb.createTemplate(width, height);

            //needed to convert and embed specific fonts into the pdf
            DefaultFontMapper fontMapper = new DefaultFontMapper() {

                @Override
                public BaseFont awtToPdf(java.awt.Font font) {
                    //special case for handwritten
                    if (font.getName().toLowerCase().startsWith("journal")) {
                        return HANDWRITTEN_FONT1_PDF;
                    } else {
                        return super.awtToPdf(font);
                    }
                }
            };

            FontFactory.registerDirectories();
            String fontsDirectoryPath = ItemPostIt.class.getResource("/org/scrinch/gui").getPath();
            fontMapper.insertDirectory(fontsDirectoryPath);
            Graphics2D g2 = tp.createGraphics(width, height, fontMapper);

            g2.translate(0, printed + topMargin);

            int j = lastPostItIndex;
            for (int i = lastItemIndex; i < items.length; i++) {
                Item item = items[i];
                if (getItemPrintValidity(item)) {
                    ItemPostIt itemPostIt = itemPostItList.get(j++);
                    Dimension dimension = new Dimension((int) width - leftMargin - rightMargin,
                            (int) (height / 5));
                    itemPostIt.setPreferredSize(dimension);
                    itemPostIt.setSize(dimension);
                    itemPostIt.setLocation(0, 0);
                    itemPostIt.doLayout();
                    itemPostIt.setSize(itemPostIt.getOptimalSize(dimension.width));
                    itemPostIt.doLayout();
                    if ((printed + itemPostIt.getHeight() + itemMargin) < height) {
                        itemPostIt.drawPaperAndShadow(g2);
                        itemPostIt.drawAllForPdf(g2);
                        printed += (itemPostIt.getHeight() + itemMargin);
                        g2.translate(0, itemPostIt.getHeight());
                    } else {
                        break;
                    }
                    lastPostItIndex++;
                }
                lastItemIndex++;
            }

            cb.addTemplate(tp, leftMargin, bottomMargin);
            iTextDocument.newPage();
            printed = 0;
            g2.dispose();
        }

    }

    private void addTitle(com.lowagie.text.Document iTextDocument) throws DocumentException, IOException {

        Paragraph paragraph = new Paragraph();
        Chunk chunk = new Chunk(getPdfHeaderTitle(),
                new com.lowagie.text.Font(HANDWRITTEN_FONT1_PDF, 24));
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.add(chunk);
        iTextDocument.add(paragraph);
    }

    protected String getPdfHeaderTitle() {
        return itemTablePanel.getItemSet().getTitle();
    }

    /**
     * In a generic item set, all items must be printed. Used by exportSetToPdfFile().
     * Override this method if you want to apply a filter to printed items (e.g. SprintPanel object)
     */
    protected boolean getItemPrintValidity(Item item) {
        return true;
    }
}
