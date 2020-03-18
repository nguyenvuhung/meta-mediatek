/**
 * @file   lte_trigger.c
 * @author Hungnv9
 * @date   15 January 2020
 * @brief  A kernel module for controlling a GPIO Pin. The device mounts devices via
 * sysfs /sys/class/gpio/gpio266. Therefore, this LKM circuit assumes that an LED
 * is attached to GPIO 49 which is on P9_23. There
 * is no requirement for a custom overlay, as the pins are in their default mux mode states.
*/

#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/gpio.h>                 // Required for the GPIO functions
#include <linux/interrupt.h>            // Required for the IRQ code

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Hungnv9");
MODULE_DESCRIPTION("A GPIO module for the Bananapi R2");
MODULE_VERSION("0.1");

static unsigned int gpiopin = 266;       ///< hard coding the gpio  (GPIO34)
static bool	    ledOn = 0;          ///< Is the LED on or off? Used to invert its state (off by default)

/// Function prototype for the custom IRQ handler function -- see below for the implementation

/** @brief The LKM initialization function
 *  The static keyword restricts the visibility of the function to within this C file. The __init
 *  macro means that for a built-in driver (not a LKM) the function is only used at initialization
 *  time and that it can be discarded and its memory freed up after that point. In this example this
 *  function sets up the GPIOs and the IRQ
 *  @return returns 0 if successful
 */
static int __init lte_trigger_init(void){
   int result = 0;
   printk(KERN_INFO "GPIO_TEST: Initializing the GPIO_TEST LKM\n");
   // Is the GPIO a valid GPIO number (e.g., the BBB has 4x32 but not all available)
   if (!gpio_is_valid(gpiopin)){
      printk(KERN_INFO "GPIO_TEST: invalid LED GPIO\n");
      return -ENODEV;
   }
   // Going to set up the LED. It is a GPIO in output mode and will be on by default
   ledOn = true;
   gpio_request(gpiopin, "sysfs");          // gpiopin is hardcoded to 266, request it
   gpio_direction_output(gpiopin, ledOn);   // Set the gpio to be in output mode and on
   gpio_set_value(gpiopin, 1);          // Not required as set by line above (here for reference)
   gpio_export(gpiopin, false);             // Causes gpio266 to appear in /sys/class/gpio
			                    // the bool argument prevents the direction from being changed

// Perform a quick test to see that the button is working as expected on LKM load
   printk(KERN_INFO "GPIO_TEST: The button state is currently: %d\n", gpio_get_value(gpiopin));
   return result;
}

/** @brief The LKM cleanup function
 *  Similar to the initialization function, it is static. The __exit macro notifies that if this
 *  code is used for a built-in driver (not a LKM) that this function is not required. Used to release the
 *  GPIOs and display cleanup messages.
 */
static void __exit lte_trigger_exit(void){
   gpio_set_value(gpiopin, 0);              // Turn the LED off, makes it clear the device was unloaded
   gpio_unexport(gpiopin);                  // Unexport the LED GPIO
   gpio_free(gpiopin);                      // Free the LED GPIO
   printk(KERN_INFO "GPIO_TEST: Goodbye from the LKM!\n");
   printk(KERN_INFO "GPIO_TEST: The button state is currently: %d\n", gpio_get_value(gpiopin));
}


/// This next calls are  mandatory -- they identify the initialization function
/// and the cleanup function (as above).
module_init(lte_trigger_init);
module_exit(lte_trigger_exit);
