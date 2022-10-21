#define PIN_ENGINE_OPEN 11
#define PIN_ENGINE_CLOSE 12
#define PIN_ACTIVATE_ENGINE 10

void setup() {
pinMode(PIN_ENGINE_OPEN, OUTPUT);
  pinMode(PIN_ENGINE_CLOSE, OUTPUT);
  pinMode(PIN_ACTIVATE_ENGINE, OUTPUT);

  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
 
    int power =128;
    Serial.println("OPEN");
    analogWrite(PIN_ACTIVATE_ENGINE,power);
    digitalWrite(PIN_ENGINE_CLOSE,LOW);
    digitalWrite(PIN_ENGINE_OPEN,HIGH);
    delay(5000);
    Serial.println("STOP");
    digitalWrite(PIN_ACTIVATE_ENGINE,LOW);
    delay(1000);
    power=255;
    Serial.println("CLOSE");
    analogWrite(PIN_ACTIVATE_ENGINE,power);
    digitalWrite(PIN_ENGINE_OPEN,LOW);
    digitalWrite(PIN_ENGINE_CLOSE,HIGH);
    delay(5000);
    Serial.println("STOP");
    digitalWrite(PIN_ACTIVATE_ENGINE,LOW);
    delay(1000);
  // put your main code here, to run repeatedly:

}
