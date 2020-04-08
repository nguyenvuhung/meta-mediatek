#!/bin/sh

# Override these variables in sourced script(s) located
# in /usr/lib/swupdate/conf.d or /etc/swupdate/conf.d
SWUPDATE_ARGS="-v -f /etc/swupdate.cfg -p 'reboot' ${SWUPDATE_EXTRA_ARGS}"
SWUPDATE_WEBSERVER_ARGS=""
SWUPDATE_SURICATTA_ARGS=""
BOARD_NAME="mt7623-bpi-r2"

# Function generate MAC address random
function mac_generate()
{
  new_mac_addr=`hexdump -n3 -e'/3 "00:60:2F" 3/1 ":%02X"' /dev/urandom`
  return 0
}


# source all files from /etc/swupdate/conf.d and /usr/lib/swupdate/conf.d/
# A file found in /etc replaces the same file in /usr
for f in `(test -d /usr/lib/swupdate/conf.d/ && ls -1 /usr/lib/swupdate/conf.d/; test -d /etc/swupdate/conf.d && ls -1 /etc/swupdate/conf.d) | sort -u`; do
  if [ -f /etc/swupdate/conf.d/$f ]; then
    . /etc/swupdate/conf.d/$f
  else
    . /usr/lib/swupdate/conf.d/$f
  fi
done

		if [ "$SWUPDATE_SURICATTA_ARGS" != "" ]; then

      # Determine MAC address for board
      # first_start = 1 => the first board is started: Generate MAC address random once and store MAC address into bootloader
      # first_start = 0 => the board don't start the first: Read MACC address from bootloader
      first_start=`fw_printenv FIRST_START 2> /dev/null | cut -f 2 -d'='`

      if [ "$first_start" == "0" ]; then
        # Read MAC_ADDR from bootloader
        mac_addr=`fw_printenv MAC_ADDR 2> /dev/null | cut -f 2 -d'='`
      else
        # setenv FIRST_START is 0
        fw_setenv FIRST_START 0
        # Generate MAC_ADDR random
        mac_generate
        mac_addr=$new_mac_addr
        # store MAC ADDR into bootloader
        fw_setenv MAC_ADDR $mac_addr
      fi

			# determine suricatta update status: '1' -> 'installed', report to
			# server
			state=`fw_printenv ustate 2> /dev/null | cut -f 2 -d'='`
      if [ "$state" == "1" ]; then
      # Confirm update status to server: SUCCESS
			  SWUPDATE_SURICATTA_ARGS="-c 2 -i $BOARD_NAME:$mac_addr"
      else
        SWUPDATE_SURICATTA_ARGS="-i $BOARD_NAME:$mac_addr"
      fi
		fi

#  handle variable escaping in a simmple way. Use exec to forward open filedescriptors from systemd open.
if [ "$SWUPDATE_WEBSERVER_ARGS" != "" -a  "$SWUPDATE_SURICATTA_ARGS" != "" ]; then
  exec /usr/bin/swupdate $SWUPDATE_ARGS -w "$SWUPDATE_WEBSERVER_ARGS" -u "$SWUPDATE_SURICATTA_ARGS"
elif [ "$SWUPDATE_WEBSERVER_ARGS" != "" ]; then
  exec /usr/bin/swupdate $SWUPDATE_ARGS -w "$SWUPDATE_WEBSERVER_ARGS"
elif [ "$SWUPDATE_SURICATTA_ARGS" != "" ]; then
  exec /usr/bin/swupdate $SWUPDATE_ARGS -u "$SWUPDATE_SURICATTA_ARGS"
else
  exec /usr/bin/swupdate $SWUPDATE_ARGS
fi
