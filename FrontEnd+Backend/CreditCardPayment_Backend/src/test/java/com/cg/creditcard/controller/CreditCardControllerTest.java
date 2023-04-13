package com.cg.creditcard.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.enums.CreditCardType;
import com.cg.creditcard.enums.UserRole;
import com.cg.creditcard.service.ICreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CreditCardController.class})
@ExtendWith(SpringExtension.class)
class CreditCardControllerTest {
  @Autowired
  private CreditCardController creditCardController;

  @MockBean
  private ICreditCardService iCreditCardService;

  @Test
  void testDeleteCreditCardByCardId() throws Exception {
    User user = new User();
    user.setAccounts(new ArrayList<>());
    user.setAddress("42 Main St");
    user.setContactNo(1L);
    user.setCreditcards(new ArrayList<>());
    user.setDob(LocalDate.ofEpochDay(1L));
    user.setEmail("jane.doe@example.org");
    user.setName("Name");
    user.setPassword("iloveyou");
    user.setRole(UserRole.CUSTOMER);
    user.setStatements(new ArrayList<>());
    user.setUserId(123L);
    user.setUserTransaction(new ArrayList<>());

    CreditCard creditCard = new CreditCard();
    creditCard.setBankName("Bank Name");
    creditCard.setCardExpiry(LocalDate.ofEpochDay(1L));
    creditCard.setCardId(123L);
    creditCard.setCardName("Card Name");
    creditCard.setCardNumber(1L);
    creditCard.setCardType(CreditCardType.VISA);
    creditCard.setCvv(1);
    creditCard.setPayments(new ArrayList<>());
    creditCard.setUser(user);
    when(this.iCreditCardService.removeCreditCard((Long) any())).thenReturn(creditCard);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/creditcard/delete/{cardId}", 123L);
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content()
        .string(
          "{\"response\":{\"cardId\":123,\"bankName\":\"Bank Name\",\"cardType\":\"VISA\",\"cardName\":\"Card Name\",\"cardNumber"
            + "\":1,\"cardExpiry\":[1970,1,2],\"cvv\":1,\"payments\":[]},\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCards() throws Exception {
    when(this.iCreditCardService.getAllCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getall");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCards2() throws Exception {
    when(this.iCreditCardService.getAllCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getall");
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsByName() throws Exception {
    when(this.iCreditCardService.getAllCreditCardsByName((String) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getall/name/{cardName}",
      "Card Name");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsByName2() throws Exception {
    when(this.iCreditCardService.getAllCreditCardsByName((String) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getall/name/{cardName}",
      "Card Name");
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsByType() throws Exception {
    when(this.iCreditCardService.getAllCreditCardsByType((CreditCardType) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getall/type/{cardType}",
      CreditCardType.VISA);
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsByType2() throws Exception {
    when(this.iCreditCardService.getAllCreditCardsByType((CreditCardType) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getall/type/{cardType}",
      CreditCardType.VISA);
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllExpiredCreditCards() throws Exception {
    when(this.iCreditCardService.getAllExpiredCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getall/expired");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllExpiredCreditCards2() throws Exception {
    when(this.iCreditCardService.getAllExpiredCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getall/expired");
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllNewCreditCards() throws Exception {
    when(this.iCreditCardService.getAllNewCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getall/new");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllNewCreditCards2() throws Exception {
    when(this.iCreditCardService.getAllNewCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getall/new");
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testFindCreditCardById() throws Exception {
    User user = new User();
    user.setAccounts(new ArrayList<>());
    user.setAddress("42 Main St");
    user.setContactNo(1L);
    user.setCreditcards(new ArrayList<>());
    user.setDob(LocalDate.ofEpochDay(1L));
    user.setEmail("jane.doe@example.org");
    user.setName("Name");
    user.setPassword("iloveyou");
    user.setRole(UserRole.CUSTOMER);
    user.setStatements(new ArrayList<>());
    user.setUserId(123L);
    user.setUserTransaction(new ArrayList<>());

    CreditCard creditCard = new CreditCard();
    creditCard.setBankName("Bank Name");
    creditCard.setCardExpiry(LocalDate.ofEpochDay(1L));
    creditCard.setCardId(123L);
    creditCard.setCardName("Card Name");
    creditCard.setCardNumber(1L);
    creditCard.setCardType(CreditCardType.VISA);
    creditCard.setCvv(1);
    creditCard.setPayments(new ArrayList<>());
    creditCard.setUser(user);
    when(this.iCreditCardService.getCreditCardById((Long) any())).thenReturn(creditCard);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/get/id/{cardId}", 123L);
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content()
        .string(
          "{\"response\":{\"cardId\":123,\"bankName\":\"Bank Name\",\"cardType\":\"VISA\",\"cardName\":\"Card Name\",\"cardNumber"
            + "\":1,\"cardExpiry\":[1970,1,2],\"cvv\":1,\"payments\":[]},\"statusCode\":1}"));
  }

  @Test
  void testFindCreditCardByNumber() throws Exception {
    User user = new User();
    user.setAccounts(new ArrayList<>());
    user.setAddress("42 Main St");
    user.setContactNo(1L);
    user.setCreditcards(new ArrayList<>());
    user.setDob(LocalDate.ofEpochDay(1L));
    user.setEmail("jane.doe@example.org");
    user.setName("Name");
    user.setPassword("iloveyou");
    user.setRole(UserRole.CUSTOMER);
    user.setStatements(new ArrayList<>());
    user.setUserId(123L);
    user.setUserTransaction(new ArrayList<>());

    CreditCard creditCard = new CreditCard();
    creditCard.setBankName("Bank Name");
    creditCard.setCardExpiry(LocalDate.ofEpochDay(1L));
    creditCard.setCardId(123L);
    creditCard.setCardName("Card Name");
    creditCard.setCardNumber(1L);
    creditCard.setCardType(CreditCardType.VISA);
    creditCard.setCvv(1);
    creditCard.setPayments(new ArrayList<>());
    creditCard.setUser(user);
    when(this.iCreditCardService.getCreditCardByNumber((Long) any())).thenReturn(creditCard);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/get/num/{cardNumber}", 1L);
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content()
        .string(
          "{\"response\":{\"cardId\":123,\"bankName\":\"Bank Name\",\"cardType\":\"VISA\",\"cardName\":\"Card Name\",\"cardNumber"
            + "\":1,\"cardExpiry\":[1970,1,2],\"cvv\":1,\"payments\":[]},\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsAboutToExpire() throws Exception {
    when(this.iCreditCardService.getAllAboutToExpireCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getall/expiresoon");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsAboutToExpire2() throws Exception {
    when(this.iCreditCardService.getAllAboutToExpireCreditCards()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getall/expiresoon");
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsByUserId() throws Exception {
    when(this.iCreditCardService.findAllCreditCardByUser((Long) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getall/userId/{userId}",
      123L);
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllCreditCardsByUserId2() throws Exception {
    when(this.iCreditCardService.findAllCreditCardByUser((Long) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getall/userId/{userId}", 123L);
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllPaymentsByCardId() throws Exception {
    when(this.iCreditCardService.findAllPaymentByCardId((Long) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcard/getallpayments/{cardId}",
      123L);
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testGetAllPaymentsByCardId2() throws Exception {
    when(this.iCreditCardService.findAllPaymentByCardId((Long) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/creditcard/getallpayments/{cardId}", 123L);
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(getResult)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("{\"response\":[],\"statusCode\":1}"));
  }

  @Test
  void testSaveCreditCard() throws Exception {
    User user = new User();
    user.setAccounts(new ArrayList<>());
    user.setAddress("42 Main St");
    user.setContactNo(1L);
    user.setCreditcards(new ArrayList<>());
    user.setDob(LocalDate.ofEpochDay(1L));
    user.setEmail("jane.doe@example.org");
    user.setName("Name");
    user.setPassword("iloveyou");
    user.setRole(UserRole.CUSTOMER);
    user.setStatements(new ArrayList<>());
    user.setUserId(123L);
    user.setUserTransaction(new ArrayList<>());

    CreditCard creditCard = new CreditCard();
    creditCard.setBankName("Bank Name");
    creditCard.setCardExpiry(LocalDate.ofEpochDay(1L));
    creditCard.setCardId(123L);
    creditCard.setCardName("Card Name");
    creditCard.setCardNumber(1L);
    creditCard.setCardType(CreditCardType.VISA);
    creditCard.setCvv(1);
    creditCard.setPayments(new ArrayList<>());
    creditCard.setUser(user);
    String content = (new ObjectMapper()).writeValueAsString(creditCard);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/creditcard/add/{userId}", 123L)
      .contentType(MediaType.APPLICATION_JSON)
      .content(content);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testUpdateCreditCard() throws Exception {
    User user = new User();
    user.setAccounts(new ArrayList<>());
    user.setAddress("42 Main St");
    user.setContactNo(1L);
    user.setCreditcards(new ArrayList<>());
    user.setDob(LocalDate.ofEpochDay(1L));
    user.setEmail("jane.doe@example.org");
    user.setName("Name");
    user.setPassword("iloveyou");
    user.setRole(UserRole.CUSTOMER);
    user.setStatements(new ArrayList<>());
    user.setUserId(123L);
    user.setUserTransaction(new ArrayList<>());

    CreditCard creditCard = new CreditCard();
    creditCard.setBankName("Bank Name");
    creditCard.setCardExpiry(LocalDate.ofEpochDay(1L));
    creditCard.setCardId(123L);
    creditCard.setCardName("Card Name");
    creditCard.setCardNumber(1L);
    creditCard.setCardType(CreditCardType.VISA);
    creditCard.setCvv(1);
    creditCard.setPayments(new ArrayList<>());
    creditCard.setUser(user);
    String content = (new ObjectMapper()).writeValueAsString(creditCard);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/creditcard/update/{userId}", 123L)
      .contentType(MediaType.APPLICATION_JSON)
      .content(content);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.creditCardController)
      .build()
      .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }
}

