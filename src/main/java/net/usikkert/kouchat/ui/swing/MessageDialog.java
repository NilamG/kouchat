
/***************************************************************************
 *   Copyright 2006-2014 by Christian Ihle                                 *
 *   contact@kouchat.net                                                   *
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import net.usikkert.kouchat.ui.swing.messages.SwingMessages;
import net.usikkert.kouchat.util.Validate;

/**
 * This is a more fancy message dialog with a header.
 *
 * @author Christian Ihle
 */
public class MessageDialog extends JDialog {

    private final JLabel appNameL, contentL;

    /**
     * Creates a new MessageDialog. To open the dialog, use setVisible().
     *
     * @param imageLoader The image loader.
     * @param swingMessages The swing messages to use for the dialog.
     */
    public MessageDialog(final ImageLoader imageLoader, final SwingMessages swingMessages) {
        super((Frame) null, true);

        Validate.notNull(imageLoader, "Image loader can not be null");
        Validate.notNull(swingMessages, "Swing messages can not be null");

        final UITools uiTools = new UITools();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(uiTools.createTitle("Missing title")); // NON-NLS - should be overwritten before use
        setResizable(false);

        final StatusIcons statusIcons = new StatusIcons(imageLoader);
        setIconImage(statusIcons.getNormalIcon());

        appNameL = new JLabel();
        appNameL.setFont(new Font("Dialog", 0, 22));
        appNameL.setIcon(statusIcons.getNormalIconImage());
        appNameL.setText("No top text"); // NON-NLS - should be overwritten before use

        final JPanel northP = new JPanel();
        northP.setBackground(Color.WHITE);
        northP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        northP.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 12));
        northP.add(appNameL);

        getContentPane().add(northP, BorderLayout.PAGE_START);

        final JButton okB = new JButton();
        okB.setText(swingMessages.getMessage("swing.button.ok"));
        okB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });

        getRootPane().setDefaultButton(okB);

        final JPanel southP = new JPanel();
        southP.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
        southP.add(okB);

        getContentPane().add(southP, BorderLayout.PAGE_END);

        final JLabel iconIconL = new JLabel();
        iconIconL.setIcon(UIManager.getIcon("OptionPane.informationIcon"));

        final JPanel leftP = new JPanel();
        leftP.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
        leftP.add(iconIconL);

        getContentPane().add(leftP, BorderLayout.LINE_START);

        contentL = new JLabel("No content"); // NON-NLS - should be overwritten before use

        final JPanel centerP = new JPanel();
        centerP.setBorder(BorderFactory.createEmptyBorder(12, 2, 0, 12));
        centerP.setLayout(new BorderLayout());
        centerP.add(contentL, BorderLayout.CENTER);

        getContentPane().add(centerP, BorderLayout.CENTER);

        // Close with Escape key
        final KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);

        final Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }

    /**
     * This is the text shown at the top (below the titlebar), to the left of the icon.
     *
     * @param text The text to show.
     */
    public void setTopText(final String text) {
        appNameL.setText(" " + text);
    }

    /**
     * This is the main content.
     *
     * @param info The text to add.
     */
    public void setContent(final String info) {
        contentL.setText(info);
    }

    /**
     * Shows the Message Dialog.
     *
     * {@inheritDoc}
     */
    @Override
    public void setVisible(final boolean visible) {
        pack();
        setLocationRelativeTo(getParent());
        super.setVisible(visible);
    }
}
