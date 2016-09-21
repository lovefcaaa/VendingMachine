package indi.cc.vendingmachine.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import indi.cc.vendingmachine.bean.AddRecords;
import indi.cc.vendingmachine.bean.Administrator;
import indi.cc.vendingmachine.bean.VendingMachine;
import indi.cc.vendingmachine.dao.AdminManageHelper;
import indi.cc.vendingmachine.util.WindowUtil;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
//������Ǯ����

public class AddCoinFrame extends javax.swing.JDialog {
    
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JTextField jTextField1;
	private JDialog jd;
	private Administrator admin;
	/**
	 * 
	 * @param owner ���ĸ�����
	 * @param title ������
	 * @param modal ָ����ģʽ���ڣ����з�ģʽ����
	 */
	public AddCoinFrame(JDialog owner, String title, boolean modal,Administrator admin){
		super(owner, title, modal);
		this.admin = admin;
		  initComponents();
		  WindowUtil.setFrameCenter(this);
	        this.setResizable(false);
	        this.setVisible(true);
	}
	
    private void initComponents() {
    	this.jd = this;
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField1.requestFocus();//��ȡ����
       

        jLabel1.setFont(new java.awt.Font("��Բ", 1, 12)); // NOI18N
        jLabel1.setText("��������Ǯ:");
        jTextField1.addKeyListener(new KeyAdapter(){  
        	//����ֻ����������
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //�ؼ������ε��Ƿ�����  
                }  
            }  
        }); 
       

        jButton1.setFont(new java.awt.Font("��Բ", 1, 12)); // NOI18N
        jButton1.setText("����");
        jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String inputValue = jTextField1.getText().trim();
				if(inputValue.equals("")||Integer.parseInt(inputValue)==0){
					JOptionPane.showMessageDialog(jd, "�������Ǯ!!");
					jTextField1.setText("");
				}else{
					AdminManageHelper helper = new AdminManageHelper();
					//��¼������Ǯ�ļ�¼
					AddRecords record = new AddRecords();
					record.setAdministrator(admin.getUsername());//��������Ա
					record.setBeforeAdd(VendingMachine.getInstance().getCoin());//��ȡ����ǰ����Ǯ���
					VendingMachine.getInstance().setCoin(Integer.parseInt(inputValue)+VendingMachine.getInstance().getCoin());
					helper.updateVendingMachine(VendingMachine.getInstance());
					JOptionPane.showMessageDialog(jd, "���Ѳ�������Ǯ:"+inputValue+"Ԫ!!");	
					record.setAddAmount(Integer.parseInt(inputValue));//�������ӵĽ��
					record.setAfterAdd(VendingMachine.getInstance().getCoin());
					helper.addAddRecords(record);	///���Ӽ�¼
					
					jd.dispose();
				}
			}
		});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }                                   


                     
}