/*
 * Copyright (C) 2015 Pankaj
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.codeforwin.projects.onscreenkeyboard;

import java.awt.AWTException;
import java.awt.Robot;
import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_CAPS_LOCK;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_NUM_LOCK;
import static java.awt.event.KeyEvent.VK_SCROLL_LOCK;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_WINDOWS;

/**
 *
 * @author Pankaj
 */
public class Keyboard {
    private static boolean IS_NUM_LOCK_ON = false;
    private static boolean IS_CAPS_LOCK_ON = false;
    private static boolean IS_SCROLL_LOCK_ON = false;
    private static boolean IS_SHIFT_PRESSED = false;
    private static boolean IS_CTRL_PRESSED = false;
    private static boolean IS_ALT_PRESSED = false;
    private static boolean IS_WIN_PRESSED = false;
    
    private static Robot robot = null;
    static { 
        try {
            robot = new Robot();
        }catch(AWTException ex) { }
    }
    
    
    private Keyboard() { }
    
    /**
     * Performs a key press and key release activity 
     * @param keycode 
     */
    public static void typeKey(int keycode) {
        pressKey(keycode);
        releaseKey(keycode);
        
        switch(keycode) {
            case VK_CAPS_LOCK:
                IS_CAPS_LOCK_ON = !IS_CAPS_LOCK_ON;
                break;
            case VK_NUM_LOCK:
                IS_NUM_LOCK_ON = !IS_NUM_LOCK_ON;
                break;
            case VK_SCROLL_LOCK:
                IS_SCROLL_LOCK_ON = !IS_SCROLL_LOCK_ON;
        }
    }
    
    /**
     * Performs a key press activity. A key pressed must be released using 
     * releaseKey()
     * @param keycode integer representing the Virtual Key code.
     */
    public static void pressKey(int keycode) {
        if(robot == null)
            throw new UnsupportedOperationException("This platform doesn't supports low level keyboard activities.");
        
        /**
         * Check if the special function key is already pressed then simply return
         */
        switch (keycode) {
            case VK_SHIFT:
                if (IS_SHIFT_PRESSED)
                    return;
                IS_SHIFT_PRESSED = true;
                break;
            case VK_CONTROL:
                if (IS_CTRL_PRESSED)
                    return;
                IS_CTRL_PRESSED = true;
                break;
            case VK_ALT:
                if (IS_ALT_PRESSED)
                    return;
                IS_ALT_PRESSED = true;
                break;
            case VK_WINDOWS:
                if(IS_WIN_PRESSED)
                    return;
                IS_WIN_PRESSED = true;
        }
        
        robot.keyPress(keycode);
    }
    
    /**
     * Releases a key that was previously pressed.
     * @param keycode integer representing the Virtual Key code.
     */
    public static void releaseKey(int keycode) {
        if(robot == null)
            throw new UnsupportedOperationException("This platform doesn't supports low level keyboard activities.");
        
        /**
         * Check if the special function key is was pressed or not. If not then simply return
         */
        switch (keycode) {
            case VK_SHIFT:
                if (!IS_SHIFT_PRESSED)
                    return;
                IS_SHIFT_PRESSED = false;
                break;
            case VK_CONTROL:
                if (!IS_CTRL_PRESSED)
                    return;
                IS_CTRL_PRESSED = false;
                break;
            case VK_ALT:
                if (!IS_ALT_PRESSED)
                    return;
                IS_ALT_PRESSED = false;
                break;
            case VK_WINDOWS:
                if(!IS_WIN_PRESSED)
                    return;
                IS_WIN_PRESSED = false;
        }
        
        robot.keyRelease(keycode);
    }
    
    /**
     * Releases all special keys that was previously pressed. This function releases
     * Alt, Control, Shift, Windows key.
     */
    public static void releaseAllSpecialKeys() {
        if(IS_ALT_PRESSED) {
            releaseKey(VK_ALT);
            IS_ALT_PRESSED = false;
        }
        if(IS_CTRL_PRESSED) {
            releaseKey(VK_CONTROL);
            IS_CTRL_PRESSED = false;
        }
        if(IS_SHIFT_PRESSED) {
            releaseKey(VK_SHIFT);
            IS_SHIFT_PRESSED = false;
        }
        if(IS_WIN_PRESSED) {
            releaseKey(VK_WINDOWS);
            IS_WIN_PRESSED = false;
        }
    }
    
    /**
     * Gets, the current status of NumLock key. Whether the key is pressed or not.
     * @return true if NumLock is on; otherwise false.
     */
    public static boolean isNumLockOn() {
        return IS_NUM_LOCK_ON;
    }
    
    /**
     * Gets, the current status of CapsLock key. Whether the key is pressed or not.
     * @return true if CapsLock is on; otherwise false.
     */
    public static boolean isCapsLockOn() {
        return IS_CAPS_LOCK_ON;
    }
    
    /**
     * Gets, the current status of ScrollLock key. Whether the key is pressed or not.
     * @return true if ScrollLock is on; otherwise false.
     */
    public static boolean isScrollLockOn() {
        return IS_SCROLL_LOCK_ON;
    }
    
    /**
     * Gets, the current status of Shift key. Whether the shift key is pressed or not.
     * @return true if the shift key is pressed; otherwise false.
     */
    public static boolean isShiftPressed() {
        return IS_SHIFT_PRESSED;
    }
    
    /**
     * Gets, the current status of Control key. Whether the control key is pressed or not.
     * @return true if Control key is pressed; otherwise false.
     */
    public static boolean isCtrlPressed() {
        return IS_CTRL_PRESSED;
    }
    
    /**
     * Gets, the current status of Alt key. Whether the Alt key is pressed or not.
     * @return true if Alt key is pressed; otherwise false.
     */
    public static boolean isAltPressed(){
        return IS_ALT_PRESSED;
    }
    
    /**
     * Gets, the current status of Windows key. Whether the Windows key is pressed 
     * or not.
     * @return true is the Win key is currently pressed; otherwise false. 
     */
    public static boolean isWinPressed() {
        return IS_WIN_PRESSED;
    }
}