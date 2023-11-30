#include <Servo.h>
Servo servomotor;
int botonera=3;
int sensor=10;
int claxon=122;
int ledDerecha=9;
int ledIzquierda=34;
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
servomotor.attach(11);
motor MM1;
}

void loop(){
servomotor.write(0);
delay(1000);
servomotor.write(30);
delay(1000);
servomotor.write(60);
delay(1000);
servomotor.write(90);
delay(1000);
tone(claxon,120024,23);
}
