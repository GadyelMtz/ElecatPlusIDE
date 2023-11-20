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
digitalWrite(ledIzquierda,HIGH);
digitalWrite(ledDerecha,LOW);
delay(500);
digitalWrite(ledIzquierda,LOW);
digitalWrite(ledDerecha,HIGH);
delay(500);
tone(claxon,900,200);
delay(500);
tone(claxon,1000,200);
delay(500);
tone(claxon,1100,200);
delay(500);
}
