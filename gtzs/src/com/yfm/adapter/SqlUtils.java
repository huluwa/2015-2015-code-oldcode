package com.yfm.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlUtils extends SQLiteOpenHelper {

	private static String name = "gt";

	public SqlUtils(Context context) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String goodstype = "create table goodstype(cgoodstype varchar(50),iindex int)";
		String goodsitem = "create table goodsitem(cgoodsname varchar(50),cgoodstype varchar(50),iindex int)";
		db.execSQL(goodstype);
		db.execSQL(goodsitem);
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('���',4);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('�����',8);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('�ְ��',3);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('�ֲ�',2);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('�ֹ�',1);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('¯��',9);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('����',10);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('�ظ�',7);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('�͸�',6);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('�к��',5);");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','���','38');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','���','39');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','���','40');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�Ͻ��','���','41');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ǿ��','���','42');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�͸�ʴ��','���','43');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���������','���','44');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','���','45');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','���','46');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���','���','47');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��п��','���','48');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��Ϳ��','���','49');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���߸�','���','50');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values(' ������','�����','96');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����ֹ�','�����','97');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������߲�','�����','98');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����ִ�','�����','99');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�����Բ��','�����','100');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����ֽǸ�','�����','101');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����ֲ۸�','�����','102');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����ֱ��','�����','103');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�������','�����','104');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ź���','�����','105');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��˿','�����','106');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ӳ��','�����','107');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values(' ���ȸ�','�����','108');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��˿','�����','109');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ְ��','26');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�Ȱ��','�ְ��','27');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ְ��','28');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','�ְ��','29');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��п���','�ְ��','30');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��Ϳ���','�ְ��','31');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ư��','�ְ��','32');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ͺϽ���','�ְ��','33');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�������','�ְ��','34');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����п���','�ְ��','35');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ϴ���','�ְ��','36');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��Ӳ��','�ְ��','37');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���Ƹ�','�ֲ�','16');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ֲ�','17');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ֲ�','18');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Բ��','�ֲ�','19');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�߲�','�ֲ�','20');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�������Ƹ�','�ֲ�','21');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�������Ƹ�','�ֲ�','22');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ֲ�','23');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ֲ�','24');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���߸�','�ֲ�','25');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�޷��','�ֹ�','1');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��п��','�ֹ�','2');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ֹ�','3');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','�ֹ�','4');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ֹ�','5');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ֱ���','�ֹ�','6');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Բ��','�ֹ�','7');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��¯��','�ֹ�','8');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ī��','�ֹ�','9');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���͹�','�ֹ�','10');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�����ù�','�ֹ�','11');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ṹ��','�ֹ�','12');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','�ֹ�','14');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ּ�','�ֹ�','15');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����޷��','�ֹ�','147');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ʯ','¯��','110');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ú��','¯��','111');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','¯��','112');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ϸ�','¯��','113');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��̿','¯��','114');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ú̿','¯��','115');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ͻ����','¯��','116');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('̼�ز���','¯��','117');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ī����','¯��','118');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ֶ�','¯��','119');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','¯��','120');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','¯��','121');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','¯��','122');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','¯��','123');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','¯��','124');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ĥ����','¯��','125');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ϸ���Ʒ','¯��','126');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','127');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','128');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','129');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','130');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','131');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','132');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','133');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','134');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','����','135');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ɫ','����','136');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ͭ','����','137');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��','����','138');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('п','����','139');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��','����','140');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��','����','141');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��','����','142');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ǧ','����','143');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��','����','144');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ϡ��','����','145');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����� ','����','146');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�ظ�','78');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Բ ��','�ظ�','79');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��и�','�ظ�','80');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('̼���','�ظ�','81');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ɸ�','�ظ�','82');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�߹���','�ظ�','83');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ģ�߸�','�ظ�','84');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','�ظ�','85');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ָ�','�ظ�','86');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�Ͻ��','�ظ�','87');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�͸�ʴ��','�ظ�','88');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ĥ��','�ظ�','89');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('̼����','�ظ�','90');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('̼�ظ�','�ظ�','91');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ȸ�','�ظ�','92');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�����','�ظ�','93');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ģ��','�ظ�','94');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ṹ��','�ظ�','95');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('H�͸�','�͸�','64');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�۸�','�͸�','65');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ָ�','�͸�','66');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�Ǹ�','�͸�','67');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���','�͸�','68');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���','�͸�','69');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ع�','�͸�','70');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�͸�','71');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ȱ߽Ǹ�','�͸�','72');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�͸�','�͸�','73');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���Ǹ�','�͸�','74');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����Բ','�͸�','75');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('̼Բ','�͸�','76');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ȱ߽Ǹ�','�͸�','77');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����','�к��','51');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('������','�к��','52');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ģ�߰�','�к��','53');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��¯��','�к��','54');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��Ե��','�к��','55');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('����ƽ��','�к��','56');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('���ư�','�к��','57');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�ͺϽ��','�к��','58');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�̰�','�к��','59');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('̼���','�к��','60');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('�Ͻ��','�к��','61');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ƽ��','�к��','62');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('��ĥ��','�к��','63');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
