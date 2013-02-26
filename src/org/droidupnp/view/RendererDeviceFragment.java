/**
 * Copyright (C) 2013 Aurélien Chabot <aurelien@chabot.fr>
 * 
 * This file is part of DroidUPNP.
 * 
 * DroidUPNP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DroidUPNP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DroidUPNP.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.droidupnp.view;

import org.droidupnp.Main;
import org.droidupnp.model.upnp.IUpnpDevice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class RendererDeviceFragment extends UpnpDeviceListFragment {

	protected static final String TAG = "RendererDeviceFragment";

	public RendererDeviceFragment()
	{
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public void refresh()
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isSelected(IUpnpDevice device)
	{
		if (Main.upnpServiceController != null && Main.upnpServiceController.getSelectedRenderer() != null)
			return device.equals(Main.upnpServiceController.getSelectedRenderer());

		return false;
	}

	@Override
	protected boolean filter(IUpnpDevice device)
	{
		return device.asService("RenderingControl");
	}

	@Override
	protected void removed(IUpnpDevice d)
	{
		if (Main.upnpServiceController != null && Main.upnpServiceController.getSelectedRenderer() != null
				&& d.equals(Main.upnpServiceController.getSelectedRenderer()))
			Main.upnpServiceController.setSelectedRenderer(null);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		Main.upnpServiceController.setSelectedRenderer(list.getItem(position).getDevice());
		Log.d(TAG, "Set renderer to " + list.getItem(position));
	}
}