/***************************************************************************
 * Copyright 2001-2007 The VietSpider         All rights reserved.  		 *
 **************************************************************************/
package org.vietspider.gui.creator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.vietspider.client.common.ClientConnector2;
import org.vietspider.client.common.source.SourcesClientHandler;
import org.vietspider.common.util.Worker;
import org.vietspider.model.Source;
import org.vietspider.ui.widget.ApplicationFactory;
import org.vietspider.ui.widget.UIDATA;
import org.vietspider.ui.widget.waiter.WaitLoading;
/** 
 * Author : Nhu Dinh Thuan
 *          nhudinhthuan@yahoo.com
 * Nov 21, 2007  
 */
public class SetDepthDialog {
  
  private Shell shell;
  private Spinner spinDepth;
  
  private String group;
  private String category;
  private String [] names; 
  
  public SetDepthDialog(Shell parent, String group, String category, String...names) {
    this.group = group;
    this.category = category;
    this.names = names;
    
    shell = new Shell(parent, SWT.CLOSE | SWT.APPLICATION_MODAL);
    shell.setLayout(new GridLayout(3, false));
    
    ApplicationFactory factory = new ApplicationFactory(shell, "Creator", getClass().getName());
    factory.createLabel("lblDepth");  
    spinDepth = factory.createSpinner(SWT.BORDER);
    spinDepth.setMinimum(0);
    spinDepth.setSelection(1);
    spinDepth.setMaximum(10000000);
    spinDepth.setIncrement(1);
    
    spinDepth.addModifyListener(new ModifyListener() {
      @SuppressWarnings("unused")
      public void modifyText(ModifyEvent event) {
        int value = spinDepth.getSelection();
        int hour = value/2;
        int minute = value%2;
        int date = 0;
        while(hour >= 24) {
          date++;
          hour -= 24;
        }
      }
    });
    
    factory.createButton("butOk", new SelectionAdapter(){
      @SuppressWarnings("unused")
      public void widgetSelected(SelectionEvent evt) { 
        shell.setVisible(false);
        setDepth();
      }      
    }, factory.loadImage("block.gif"));
    
    Rectangle displayRect = UIDATA.DISPLAY.getBounds();
    int x = (displayRect.width - 350) / 2;
    int y = (displayRect.height - 200)/ 2;
    shell.setImage(parent.getImage());
    shell.setLocation(x, y);
    shell.pack();
//    XPWindowTheme.setWin32Theme(shell);
    shell.open();
  }
  
  public void setDepth() {
    Worker excutor = new Worker() {
      
      private String message = "";
      
      private int depth = 1;

      public void abort() {
        ClientConnector2.currentInstance().abort();
      }

      public void before() {
        depth = spinDepth.getSelection();
      }

      public void execute() {
        try {
          SourcesClientHandler client = new SourcesClientHandler(group);
          for(String name : names) {
            Source source = client.loadSource(category, name);
            if(source == null) continue;
            source.setDepth(depth);
            client.saveSource(source);
          }
        } catch (Exception e) {
          if(e.getMessage() != null && e.getMessage().trim().length() > 0) {
            message = e.getMessage();
          } else {
            message = e.toString();
          }
        }
      }

      public void after() {
        if(message != null && !message.trim().isEmpty()) {
          MessageBox msg = new MessageBox (shell, SWT.APPLICATION_MODAL | SWT.OK);
          msg.setMessage(message);
          msg.open();
        }
        shell.dispose();
      }
    };
    WaitLoading loading = new WaitLoading(shell, excutor);
    loading.open();
  }
}
