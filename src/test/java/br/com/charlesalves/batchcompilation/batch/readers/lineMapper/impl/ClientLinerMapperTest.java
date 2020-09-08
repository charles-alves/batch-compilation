package br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.charlesalves.batchcompilation.domain.Client;

public class ClientLinerMapperTest {

	@Test
	public void mapAVAlidClient() {
		ClientLineMapper clientLinerMapper = new ClientLineMapper();

		Client client = (Client) clientLinerMapper.map(new String[]{"003", "00000000000", "Jos� Pedro", "Rural"});

		assertThat(client.getCpf(), is(equalTo("00000000000")));
		assertThat(client.getName(), is(equalTo("Jos� Pedro")));
		assertThat(client.getBusinessSite(), is(equalTo("Rural")));
	}

}
