#include <16f877a.h>
#fuses XT, NOWDT
#use delay (clock=4MHz)

#int_ext
void int_RB0() {
   output_toggle(pin_B7);
}

void main() {
   set_tris_B(0x01);
   output_low(pin_B7);
   port_B_pullups(true);
   enable_interrupts(int_ext);
   ext_int_edge(H_to_L);
   enable_interrupts(global);
   
   while (true){}
}
