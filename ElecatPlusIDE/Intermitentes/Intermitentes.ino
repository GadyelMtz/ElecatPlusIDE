const int btnDerecha = 4;
const int btnIzquierda = 3;
const int btnIntermitentes = 2;
const int ledDerecho = 7;
const int ledIzquierdo = 8;

bool ledDerechoParpadeando = false;
bool ledIzquierdoParpadeando = false;
bool intermitentesActivados = false;

bool estado = false;

void setup() {
  Serial.begin(9600);
  pinMode(btnDerecha, INPUT);
  pinMode(btnIzquierda, INPUT);
  pinMode(btnIntermitentes, INPUT);
  pinMode(ledDerecho, OUTPUT);
  pinMode(ledIzquierdo, OUTPUT);
}

void loop() {
  int lecturaDerecha = digitalRead(btnDerecha);
  int lecturaIzquierda = digitalRead(btnIzquierda);
  int lecturaIntermitentes = digitalRead(btnIntermitentes);
  
  delay(100);
  if(lecturaDerecha == HIGH)
  {
    ledDerechoParpadeando = true;
    ledIzquierdoParpadeando = false;
    intermitentesActivados = false;
    bounce();
  }

  if(lecturaIzquierda == HIGH)
  {
    ledDerechoParpadeando = false;
    ledIzquierdoParpadeando = true;
    intermitentesActivados = false;
    bounce();
  }

  
  if(lecturaIntermitentes == HIGH)
  {
    ledDerechoParpadeando = false;
    ledIzquierdoParpadeando = false;
    intermitentesActivados = true;
    bounce();
  }


  if(intermitentesActivados && estado){
    parpadearLedIntermitente();
  } else if(ledIzquierdoParpadeando && estado)
  {
    parpadearLed(ledIzquierdo);
  }else if(ledDerechoParpadeando && estado){
    parpadearLed(ledDerecho);
  }
  else if(estado){
    apagarLeds();
}
}

void apagarLeds(){
  digitalWrite(ledDerecho, LOW);
  digitalWrite(ledIzquierdo, LOW);
}

void parpadearLedIntermitente() {
  digitalWrite(ledIzquierdo, HIGH);
  digitalWrite(ledDerecho, HIGH);
  delay(500);
  digitalWrite(ledDerecho, LOW);
  digitalWrite(ledIzquierdo, LOW);
}

void parpadearLed(int led){
  digitalWrite(led, HIGH);
  delay(500);
  digitalWrite(led, LOW);  
}

void bounce(){
  static unsigned long previousMillis = 0;
    unsigned long currentMillis = millis();
    if (currentMillis - previousMillis >= 500) {
      previousMillis = currentMillis;
      estado = !estado;  
    }
}
