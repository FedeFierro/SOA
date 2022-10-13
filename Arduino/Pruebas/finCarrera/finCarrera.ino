#define PIN_OPEN 3
#define PIN_CLOSE 13
void setup() {
  pinMode(PIN_OPEN, INPUT);
  pinMode(PIN_CLOSE, INPUT);
  // put your setup code here, to run once:
  Serial.begin(9600);
}
int open_val=0;
int close_val=0;

void loop() {
  int old_open = open_val;
  int old_close = close_val;
  close_val = digitalRead(PIN_CLOSE);
  open_val = digitalRead(PIN_OPEN);
  if(open_val!= old_open){
  Serial.print("OPEN: ");
  Serial.println(open_val);
  }
  if(close_val!= old_close){
  Serial.print("Close: ");
  Serial.println(close_val);
  }
  // put your main code here, to run repeatedly:

}
