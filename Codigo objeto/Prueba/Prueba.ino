int sensor=10;
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C display(0x27, 16, 2);
int claxon=12;
int ledDerecha=9;
int ledIzquierda=8;
int variable1=0;
void setup(){
pinMode(ledIzquierda,OUTPUT);
pinMode(ledDerecha,OUTPUT);
pinMode(claxon,OUTPUT);
display.init();
display.backlight();

//pinMode(sensor,INPUT);
}
void loop(){
variable1 = 0;
while(variable1<=3){
digitalWrite(ledIzquierda,HIGH);
delay(250);
digitalWrite(ledIzquierda,LOW);
delay(250);
variable1 = variable1 + 1;
display.clear(); 
display.print("Este es un mensa");
display.setCursor(0, 1);
display.print("je de mas de 16 caracteres");

delay(750);
}

while(variable1>3&&variable1<6){
String mensajeLeds2 = "ON leds derechos es mas de 16 caracteres";
digitalWrite(ledDerecha,HIGH);
delay(500);
digitalWrite(ledDerecha,LOW);
delay(500);
variable1 = variable1 + 1;
display.clear(); 
display.print("ON leds derechos");
display.setCursor(0, 1);
display.print(" es mas de 16 caracteres");

delay(750);
}
}
int valor;
void pitarVeces(int veces){
for(int i=0;i<3;i=i+1){
tone(claxon,1000,100);
//valor=digitalRead(sensor);
delay(250);
}
}
