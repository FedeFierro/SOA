#define PIN_LIGHT_RED 13
#define PIN_LIGHT_GREEN 12
#define PIN_BUTTON  7
#define STATE_GREEN 1
#define STATE_RED 2
#define ANALOG_HIGH 255
#define ANALOG_INTERVAL 64
#define ANALOG_LOW 0
#define ANALOG_
#define RED_LIGHT_UPPER 1
#define RED_LIGHT_LOWER 2


int button_val=0;
int button_old_val=0;
int state =0;
int red_state=0;
int red_cont=0;

void swithligth()
{
  switch(state)
  {
    case STATE_RED:
      Serial.println("To Green");
      state = STATE_GREEN;
      digitalWrite(PIN_LIGHT_RED, LOW);
      digitalWrite(PIN_LIGHT_GREEN, HIGH);
      red_cont=0;
    break;
    case STATE_GREEN:
      Serial.println("To Red");
      state = STATE_RED;
      digitalWrite(PIN_LIGHT_GREEN, LOW);
    break;
    default:
      Serial.println("Invalid State");
    break;
  }

}

void pwm_red()
{
  if(red_cont >= ANALOG_HIGH)
  {
    red_cont = ANALOG_HIGH;
    red_state = RED_LIGHT_LOWER;
  }
  
  if(red_cont <= ANALOG_LOW)
  {
    red_cont = ANALOG_LOW;
    red_state = RED_LIGHT_UPPER;
  }
  int int_part = red_cont / ANALOG_INTERVAL; 
  int brigth = ANALOG_INTERVAL +(int_part * ANALOG_INTERVAL);
  analogWrite(PIN_LIGHT_RED,brigth); 
  switch(red_state)
  {
    case RED_LIGHT_UPPER:
      red_cont++;
      break;
    case RED_LIGHT_LOWER:
      red_cont-- ;
      break;
    default:
      Serial.println("INVALID RED STATE");
  }
}

void setup()
{
  Serial.begin(9600);
  Serial.println("iniciando..");
  pinMode(PIN_LIGHT_RED, OUTPUT);
  pinMode(PIN_LIGHT_GREEN, OUTPUT);
  pinMode(PIN_BUTTON, INPUT);
  state =STATE_RED;
  swithligth();
}

void loop()
{
 
  button_val = digitalRead(PIN_BUTTON);
  if ((button_val == HIGH) && (button_old_val == LOW))
  {
    Serial.println("Has Change");
    swithligth();
    
  }
  if(state==STATE_RED)
  {
    pwm_red();
  }
  button_old_val = button_val;
}
