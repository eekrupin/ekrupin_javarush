package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by ekrupin on 30.09.2016.
 */
public class Controller {

    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void resetDocument(){
        if (document!=null){
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument)new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text){
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

    }

    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int num = jFileChooser.showOpenDialog(view);
        if (num == JFileChooser.APPROVE_OPTION){
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader fileReader = new FileReader(currentFile)){
                new HTMLEditorKit().read(fileReader, document, 0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {

        if (currentFile == null) {
            saveDocumentAs();
        }
        else {
            //Переключать представление на html вкладку
            view.selectHtmlTab();

            //Создавать FileWriter на базе currentFile
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                //Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText()
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int num = jFileChooser.showSaveDialog(view);
        if (num == JFileChooser.APPROVE_OPTION){
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile)){
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }
}
