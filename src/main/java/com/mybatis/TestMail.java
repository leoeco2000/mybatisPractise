package com.mybatis;

import java.util.List;
import org.junit.Test;
import com.mybatis.common.user.entity.Mail;
import com.mybatis.common.user.mapper.MailMapper;

public class TestMail {

  private static MailMapper mailMapper;

  static {
    mailMapper = new MailDaoImpl();
  }

  @Test
  public void testInsert() {
    Mail mail1 = new Mail(1, "123@sina.com", "个人使用");
    Mail mail2 = new Mail(2, "123@qq.com", "企业使用");
    Mail mail3 = new Mail(3, "123@sohu.com", "注册账号使用");
    System.out.println(mailMapper.insertMail(mail1));
    System.out.println(mailMapper.insertMail(mail2));
    System.out.println(mailMapper.insertMail(mail3));
  }

  @Test
  public void testDelete() {
    System.out.println(mailMapper.deleteMail(1));
  }

  @Test
  public void testUpdate() {
    Mail mail = new Mail(2, "123@qq.com", "个人使用");
    mail.setId(2);
    System.out.println(mailMapper.updateMail(mail));
    System.out.println(mailMapper.selectMailById(2));
  }

  @Test
  public void testSelectOne() {
    System.out.println(mailMapper.selectMailById(2));
  }

  @Test
  public void testSelectList() {
    List<Mail> mailList = mailMapper.selectMailList();
    if (mailList != null && mailList.size() != 0) {
      for (Mail mail : mailList) {
        System.out.println(mail);
      }
    }
  }

}
