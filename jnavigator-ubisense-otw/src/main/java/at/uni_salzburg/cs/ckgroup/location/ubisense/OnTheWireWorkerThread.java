/*
 * @(#) OnTheWireWorkerThread.java
 *
 * This code is part of the JAviator project: javiator.cs.uni-salzburg.at
 * Copyright (c) 2009  Clemens Krainer
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package at.uni_salzburg.cs.ckgroup.location.ubisense;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import at.uni_salzburg.cs.ckgroup.io.IConnection;

/**
 * This class implements a socket server worker thread that inherits an
 * established connection. It is this class that sends the GPS data to a
 * TCP/IP client.
 */
public class OnTheWireWorkerThread extends Thread {
	
	/**
	 * The inherited connection to read from.
	 */
	private IConnection service;
	
    /**
     * The inherited socket with an established connection.
     */
    private Socket socket;

    /**
	 * Constructor.
	 * 
	 * @param socket
	 *            a socket containing an already established connection to a
	 *            client.
	 */
	public OnTheWireWorkerThread (IConnection service, Socket socket) {
		this.service = service;
		this.socket = socket;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run ()
    {
		System.out.println ("GPS receiver: New client connected, id=" + this.getName());
		
    	try {
        	int ch;
        	InputStream gpsReceiver = service.getInputStream();
        	OutputStream sockOut = socket.getOutputStream(); 

    		while ((ch = gpsReceiver.read()) > 0)
    			sockOut.write(ch);

        } catch (IOException e) {
        	if (e instanceof SocketException && e.getMessage().equals("Broken pipe"))
        		System.out.println ("GPS receiver: Client disconnected, id=" + this.getName());
        	else
        		e.printStackTrace();            		
        }
    	
        try {
          socket.close ();
        } catch (IOException e) {}
    }
	
}