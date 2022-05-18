package com.example.app;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class HelloController {
    @FXML
    Button encryptionButton;
    @FXML
    Button decryptionButton;
    @FXML
    TextField encryptionText;
    @FXML
    TextArea DecryptArea;

    @FXML
    TextField decryptionText;

    @FXML
    TextArea EncryptArea;

    final char[] rusAlphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л',
            'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ы', 'ъ',
            'э', 'ю', 'я','А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
            'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ', 'Э',
            'Ю', 'Я',' ','.','?','!','"',':','-'};


    public void ClickEncryptButton(){
        String textEncrypt =readFile(encryptionText.getText());
        if (textEncrypt==null){
            return;
        }
        char[] text = textEncrypt.toCharArray();
        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < rusAlphabet.length; j++) {
                if (text[i]==rusAlphabet[j]){
                    if ((j+3)<rusAlphabet.length) {
                        text[i] = rusAlphabet[j+3];
                        break;
                    } else {
                        text[i] = rusAlphabet[j+3 - rusAlphabet.length];
                    }

                }
            }
        }
        textEncrypt = String.copyValueOf(text);
        DecryptArea.setText(textEncrypt);
    }

    public void ClickDecryptButton (){

        String textEncrypt = readFile(decryptionText.getText());
        if (textEncrypt==null){
            return;
        }
        int count = 3;
        char[] text = textEncrypt.toCharArray();

            text = decryptionByValue(text,count);

        textEncrypt = String.copyValueOf(decryptionByValue(text,3));
        EncryptArea.setText(textEncrypt);
    }

    private char[] decryptionByValue (char[] text, int value){

        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < rusAlphabet.length; j++) {
                if (text[i]==rusAlphabet[j]){
                    if ((j-value)>=0) {
                        text[i] = rusAlphabet[j-value];
                        break;
                    } else {
                        text[i] = rusAlphabet[j - value + rusAlphabet.length-1];
                    }

                }
            }
        }
        return text;
    }

    private String readFile(String fileLocation){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Не верный путь к файлу");
      //  alert.setContentText("Не верный путь к файлу");
        if (!Files.isRegularFile(Path.of(fileLocation))){
            alert.showAndWait();
            return null;
        }
        List<String> bufferString = new ArrayList<>();
        try {
            bufferString = Files.readAllLines(Path.of(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String stringReturn = "";
        for (int i = 0; i < bufferString.size(); i++) {
            stringReturn = stringReturn + bufferString.get(i);
        }
        return stringReturn;
    }

}