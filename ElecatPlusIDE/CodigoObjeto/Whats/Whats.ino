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

delay(2000);

}
