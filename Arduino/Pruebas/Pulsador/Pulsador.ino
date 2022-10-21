#define PIN_PULSADOR 2

void setup() {
  pinMode(PIN_PULSADOR, INPUT);
  // put your setup code here, to run once:
  Serial.begin(9600);
}
int open_val=0;

void loop() {
  int old_open = open_val;
  open_val = digitalRead(PIN_PULSADOR);
  if(open_val!= old_open){
  Serial.print("OPEN: ");
  Serial.println(open_val);
  }
 
  // put your main code here, to run repeatedly:

}
