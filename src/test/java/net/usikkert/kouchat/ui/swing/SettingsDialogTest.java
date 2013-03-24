
/***************************************************************************
 *   Copyright 2006-2013 by Christian Ihle                                 *
 *   kontakt@usikkert.net                                                  *
 *                                                                         *
 *   This file is part of KouChat.                                         *
 *                                                                         *
 *   KouChat is free software; you can redistribute it and/or modify       *
 *   it under the terms of the GNU Lesser General Public License as        *
 *   published by the Free Software Foundation, either version 3 of        *
 *   the License, or (at your option) any later version.                   *
 *                                                                         *
 *   KouChat is distributed in the hope that it will be useful,            *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU      *
 *   Lesser General Public License for more details.                       *
 *                                                                         *
 *   You should have received a copy of the GNU Lesser General Public      *
 *   License along with KouChat.                                           *
 *   If not, see <http://www.gnu.org/licenses/>.                           *
 ***************************************************************************/

package net.usikkert.kouchat.ui.swing;

import static org.mockito.Mockito.*;

import net.usikkert.kouchat.misc.Settings;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test of {@link SettingsDialog}.
 *
 * @author Christian Ihle
 */
public class SettingsDialogTest {

    @Test
    @Ignore("Run manually to see the settings dialog")
    public void showSettings() {
        final SettingsDialog settingsDialog = new SettingsDialog(new ImageLoader(), new Settings());

        final Mediator mediator = mock(Mediator.class);
        when(mediator.changeNick(anyString())).thenReturn(true);
        settingsDialog.setMediator(mediator);

        settingsDialog.showSettings();
    }
}
