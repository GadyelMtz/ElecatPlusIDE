int botonera=3;
int sensor=10;
int claxon=12;
int ledDerecha=9;
int ledIzquierda=8;
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C display(0x27, 16, 2);
int variable1=0;
void setup(){
display.init();
display.backlight();
pinMode(ledIzquierda,OUTPUT);
pinMode(ledDerecha,OUTPUT);
pinMode(claxon,OUTPUT);
pinMode(sensor,INPUT);
pinMode(botonera,INPUT);
}

void loop(){

int variable;
variable=digitalRead(sensor);
int lecturaBoton;
lecturaBoton=analogRead(botonera);
delay(500);

int boton1 = 203;
int boton2 = 254;
int boton3 = 340;
int boton4 = 511;
if(lecturaBoton == boton1){
display.clear(); 
display.print("Has presionado e");
display.setCursor(0, 1);
display.print("l boton1");
delay(2500);
display.clear(); 
display.print("");
} else if(lecturaBoton == boton2){
display.clear(); 
display.print("Has presionado e");
display.setCursor(0, 1);
display.print("l boton2");
delay(2500);
display.clear(); 
display.print("");
} else if(lecturaBoton == boton3){
digitalWrite(ledIzquierda,HIGH);
delay(500);
digitalWrite(ledIzquierda,LOW);
} else if(lecturaBoton == boton4){
digitalWrite(ledDerecha,HIGH);
delay(500);
digitalWrite(ledDerecha,LOW);
}

}
