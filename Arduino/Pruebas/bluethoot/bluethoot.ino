#include <SoftwareSerial.h>

#define PIN_TX_BT_RX 8
#define PIN_RX_BT_TX 9
#define baudrate 9600
#define FLAG_INFO_UPPER 'I'
#define FLAG_INFO_LOWER 'i'
#define FLAG_ACTION_UPPER 'A'
#define FLAG_ACTION_LOWER 'a'


SoftwareSerial hc05(PIN_RX_BT_TX ,PIN_TX_BT_RX);
char flag;
void setup() {
  pinMode(PIN_RX_BT_TX, INPUT);
  pinMode(PIN_TX_BT_RX, OUTPUT);
  Serial.begin(9600); 
  hc05.begin(9600); 
  Serial.println("Ready to connect\nDefualt password is 1234 or 000");

}

void loop() {
  flag="";  
   if (hc05.available()> 0){       
    flag = hc05.read(); 
    Serial.print("Flag: ");
    Serial.println(flag);
    switch(flag){
      case FLAG_INFO_UPPER:
      case FLAG_INFO_LOWER:
        hc05.write("Info\n");
        break;
      case FLAG_ACTION_UPPER:
      case FLAG_ACTION_LOWER:
        Serial.print("Data recived: ");
        Serial.println("Action");
        break;
      default:
        break;                
    }  
   }
   
   //hc05.write("hello\n");
   
}
