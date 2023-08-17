package com.evonem.customer;

import com.evonem.exception.DuplicateResourceException;
import com.evonem.exception.RequestValidationException;
import com.evonem.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService underTest;
    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDao);
    }

    @Test
    void getAllCustomers() {
        //When
        underTest.getAllCustomers();
        //Then
        verify(customerDao).selectAllCustomers();
    }

    @Test
    void CanGetCustomer() {
        //Given
        int id = 10;
        Customer customer = new Customer(
                id,"Alex","alex@gmail.com",19
        );
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));
        //When
        Customer actual = underTest.getCustomerById(id);

        //Then
        assertThat(actual).isEqualTo(customer);

    }

    @Test
    void willThrowWhenGetCustomerReturnsEmptyOptional() {
        //Given
        int id = 10;
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(()-> underTest.getCustomerById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Customer with id [%s] does not exist".formatted(id));

    }

    @Test
    void addCustomer() {
        //Given
        String email = "alex@gmail.com";
        when(customerDao.existsCustomerWithEmail(email)).thenReturn(false);
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                "Alex",email,19
        );
        //When
        underTest.addCustomer(customerRegistrationRequest);
        //Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(
                Customer.class
        );
        verify(customerDao).insertCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getId()).isNull();
        assertThat(capturedCustomer.getName()).isEqualTo(customerRegistrationRequest.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(customerRegistrationRequest.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(customerRegistrationRequest.age());
    }

    @Test
    void WillThrowWhenEmailExistsWhileAddingCustomer() {
        //Given
        String email = "alex@gmail.com";
        when(customerDao.existsCustomerWithEmail(email)).thenReturn(true);
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                "Alex",email,19
        );
        //When
        assertThatThrownBy(()-> underTest
                .addCustomer(customerRegistrationRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Email already taken");
        //Then
        verify(customerDao, never()).insertCustomer(any());
    }


    @Test
    void deleteCustomerById() {
        //Given
        int id = 10;
        when(customerDao.existsCustomerWithId(id)).thenReturn(true);
        //When
        underTest.deleteCustomerById(id);
        //Then
        verify(customerDao).deleteCustomerById(id);
    }

    @Test
    void WillThrowWhenDeleteCustomerByIdNotExists() {
        //Given
        int id = 10;
        when(customerDao.existsCustomerWithId(id)).thenReturn(false);
        //When
        assertThatThrownBy(()-> underTest.deleteCustomerById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Customer with id [%s] does not exist".formatted(id));
        //Then
        verify(customerDao,never()).deleteCustomerById(id);
    }


    @Test
    void updateCustomer() {
        //Given
        int id = 10;
        Customer customer = new Customer(
                id,"Alex","alex@gmail.com",19
        );
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));
        String newEmail = "alexandro@amigoscode.com";
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest("Alexandro", newEmail,23);
        when(customerDao.existsCustomerWithEmail(newEmail)).thenReturn(false);
        //When
        underTest.updateCustomer(id, updateRequest);
        //Then
        ArgumentCaptor<Customer> argumentCaptor  = ArgumentCaptor.forClass(Customer.class);
        verify(customerDao).updateCustomer(argumentCaptor.capture());
        Customer capturedCustomor = argumentCaptor.getValue();

        assertThat(capturedCustomor.getName()).isEqualTo(updateRequest.name());
        assertThat(capturedCustomor.getEmail()).isEqualTo(updateRequest.email());
        assertThat(capturedCustomor.getAge()).isEqualTo(updateRequest.age());
    }

    @Test
    void CanUpdateOnlyCustomerName() {
        //Given
        int id = 10;
        Customer customer = new Customer(
                id,"Alex","alex@gmail.com",19
        );
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest("Alexandro", null,null);
        //When
        underTest.updateCustomer(id, updateRequest);
        //Then
        ArgumentCaptor<Customer> argumentCaptor  = ArgumentCaptor.forClass(Customer.class);
        verify(customerDao).updateCustomer(argumentCaptor.capture());
        Customer capturedCustomor = argumentCaptor.getValue();

        assertThat(capturedCustomor.getName()).isEqualTo(updateRequest.name());
        assertThat(capturedCustomor.getEmail()).isEqualTo(customer.getEmail());
        assertThat(capturedCustomor.getAge()).isEqualTo(customer.getAge());
    }

    @Test
    void CanUpdateOnlyCustomerAge() {
        //Given
        int id = 10;
        Customer customer = new Customer(
                id,"Alex","alex@gmail.com",19
        );
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(null, null,20);
        //When
        underTest.updateCustomer(id, updateRequest);
        //Then
        ArgumentCaptor<Customer> argumentCaptor  = ArgumentCaptor.forClass(Customer.class);
        verify(customerDao).updateCustomer(argumentCaptor.capture());
        Customer capturedCustomor = argumentCaptor.getValue();

        assertThat(capturedCustomor.getName()).isEqualTo(customer.getName());
        assertThat(capturedCustomor.getEmail()).isEqualTo(customer.getEmail());
        assertThat(capturedCustomor.getAge()).isEqualTo(updateRequest.age());
    }

    @Test
    void CanUpdateOnlyCustomerEmail() {
        //Given
        int id = 10;
        Customer customer = new Customer(
                id,"Alex","alex@gmail.com",19
        );
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));
        String newEmail = "alexandro@amigoscode.com";
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(null, newEmail,null);
        when(customerDao.existsCustomerWithEmail(newEmail)).thenReturn(false);
        //When
        underTest.updateCustomer(id, updateRequest);
        //Then
        ArgumentCaptor<Customer> argumentCaptor  = ArgumentCaptor.forClass(Customer.class);
        verify(customerDao).updateCustomer(argumentCaptor.capture());
        Customer capturedCustomor = argumentCaptor.getValue();

        assertThat(capturedCustomor.getName()).isEqualTo(customer.getName());
        assertThat(capturedCustomor.getEmail()).isEqualTo(updateRequest.email());
        assertThat(capturedCustomor.getAge()).isEqualTo(customer.getAge());
    }

    @Test
    void willThrowWhenTryingToUpdateCustomerWhenAlreadyTaken() {
        //Given
        int id = 10;
        Customer customer = new Customer(
                id,"Alex","alex@gmail.com",19
        );
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));
        String newEmail = "alexandro@amigoscode.com";
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest("Alexandro", newEmail,23);
        when(customerDao.existsCustomerWithEmail(newEmail)).thenReturn(true);
        //When
        assertThatThrownBy(()->underTest.updateCustomer(id,updateRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("email already taken");
        //Then
        verify(customerDao,never()).updateCustomer(any());
    }

    @Test
    void willThrowWhenCustomerUpdateHasNoChanges() {
        //Given
        int id = 10;
        Customer customer = new Customer(
                id,"Alex","alex@gmail.com",19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(customer.getName(), customer.getEmail(), customer.getAge());

        //When
        assertThatThrownBy(()-> underTest.updateCustomer(id,updateRequest))
                .isInstanceOf(RequestValidationException.class)
                .hasMessageContaining("no data changes found");
        //Then
        verify(customerDao,never()).updateCustomer(any());
    }

}