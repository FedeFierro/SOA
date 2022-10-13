#include <SoftwareSerial.h>

#define TX_PIN 9
#define RX_PIN 8
#define baudrate 9600

SoftwareSerial hc05(RX_PIN ,TX_PIN);
char flag;
void setup() {
  pinMode(RX_PIN, INPUT);
  pinMode(TX_PIN, OUTPUT);
  Serial.begin(9600); 
  hc05.begin(9600); 
  Serial.println("Ready to connect\nDefualt password is 1234 or 000");

}

void loop() {
   if (hc05.available()> 0){
   Serial.println("Data:");
   while(hc05.available()> 0){
    flag = hc05.read(); 
    Serial.print(flag);
   }
   }
   
   //hc05.write("hello\n");
   
}
