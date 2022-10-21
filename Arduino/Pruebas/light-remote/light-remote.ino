#include <IRremote.h>  //Libreria sensor IR
#define PIN_BUTTON 7
#define PIN_LED 4
#define PIN_IR 8
#define STATE_ON 1
#define STATE_OFF 0


int state =0;
int button_val=0;
int button_old_val =0;
int remote_val =0;
//IRrecv irrecv(PIN_IR);

void swithligth()
{
  if(state == STATE_ON)
  {
    Serial.println("OFF");
    state= STATE_OFF;
    digitalWrite(PIN_LED,LOW);
  }else
  {
    Serial.println("ON");
    state= STATE_ON;
    digitalWrite(PIN_LED,HIGH);
  }
}
void setup()
{
  Serial.begin(9600);
  Serial.println("Start..");
  IrReceiver.begin(PIN_IR, ENABLE_LED_FEEDBACK); 
  pinMode(PIN_BUTTON,INPUT);
  pinMode(PIN_LED, OUTPUT);
}
int readRemote()
{
  int value = LOW;
  if (IrReceiver.decode())
  {
    Serial.println(IrReceiver.decodedIRData.command);
    value = HIGH;
    IrReceiver.resume();
  }
  return value;
}

void loop()
{
  remote_val = readRemote();  
  button_val = digitalRead(PIN_BUTTON);
  if (((button_val == HIGH) ||(remote_val == HIGH))&& (button_old_val == LOW))
  {
    Serial.println("Has Change");
    swithligth();
    
  }
  button_old_val = button_val || remote_val;
}
