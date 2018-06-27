package com.example.victor.licenta.modelClass;

public class InformerFactory {


     public static INformer getInformer(String text){
         INformer a = null;
         if(text.contains("@")){
             a = new EmailInformer(text);
         }else if(text.contains("07")){
             a = new TextMessageInformer(text);

         }
         return a;

     }
}
