/*
 * @(#) IControlAlgorithm.java
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
package at.uni_salzburg.cs.ckgroup.control;

import at.uni_salzburg.cs.ckgroup.communication.data.CommandData;
import at.uni_salzburg.cs.ckgroup.communication.data.MotorSignals;
import at.uni_salzburg.cs.ckgroup.communication.data.SensorData;
import at.uni_salzburg.cs.ckgroup.communication.data.TrimValues;
import at.uni_salzburg.cs.ckgroup.course.PolarCoordinate;
import at.uni_salzburg.cs.ckgroup.course.VehicleStatus;

/**
 * This interface summarizes the functionality of a control algorithm for the
 * <code>JControl</code> controller.
 * 
 * @author Clemens Krainer
 */
public interface IControlAlgorithm {

	/**
	 * Apply the currently available <code>SensorData</code> from the JAviator
	 * and the <code>NavigationData</code> from the control terminal to the
	 * control algorithm for manual flight.
	 * 
	 * @param sensorData the currently available <code>SensorData</code>.
	 * @param navigationData the commanded <code>NavigationData</code>
	 * @return the new <code>MotorSignals</code>.
	 */
	public MotorSignals apply (SensorData sensorData, CommandData navigationData);

	/**
	 * Apply the currently available <code>SensorData</code> from the JAviator,
	 * the set course data and the current vehicle status to the control
	 * algorithm for autopilot flight.
	 *
	 * @param sensorData the currently available <code>SensorData</code>.
	 * @param setCourseData the position and orientation from the set course.
	 * @param currentPosition the current position as a WGS84 coordinate
	 * @param courseOverGround the course over ground in degrees: 0° North, 
	 * @param speedOverGround the current speed over ground
	 * @return the new <code>MotorSignals</code>.
	 */
	public MotorSignals apply (SensorData sensorData, VehicleStatus setCourseData, PolarCoordinate currentPosition, Double courseOverGround, Double speedOverGround);
	
	/**
	 * Set the new <code>TrimValues</code>.
	 *  
	 * @param trimValues the new <code>TrimValues</code>.
	 */
	public void setTrimValues (TrimValues trimValues);
}
