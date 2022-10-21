#define PIN_RED 4
#define PIN_GREEN 5

void setup() {
  pinMode(PIN_RED, OUTPUT);
  pinMode(PIN_GREEN, OUTPUT);

  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  digitalWrite(PIN_RED,HIGH);
  delay(1000);
  digitalWrite(PIN_RED, LOW);
  //digitalWrite(PIN_GREEN,HIGH);
  delay(1000);
  digitalWrite(PIN_GREEN, LOW);
 
  // put your main code here, to run repeatedly:

}
