#include <Servo.h>
Servo servomotor;
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
servomotor.attach(11);
}

void loop(){
servomotor.write(0);
delay(1000);
servomotor.write(10);
delay(1000);
servomotor.write(20);
delay(1000);
servomotor.write(30);
delay(1000);
tone(claxon,1000,1000);

}
