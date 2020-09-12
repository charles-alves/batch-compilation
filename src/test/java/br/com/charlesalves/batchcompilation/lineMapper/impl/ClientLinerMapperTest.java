package br.com.charlesalves.batchcompilation.lineMapper.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.charlesalves.batchcompilation.domain.Client;
import br.com.charlesalves.batchcompilation.lineMappers.impl.ClientLineMapper;

public class ClientLinerMapperTest {

	@Test
	public void mapAVAlidClient() {
		ClientLineMapper clientLinerMapper = new ClientLineMapper();

		Client client = (Client) clientLinerMapper.map(new String[]{"003", "00000000000", "José Pedro", "Rural"});

		assertThat(client.getCpf(), is(equalTo("00000000000")));
		assertThat(client.getName(), is(equalTo("José Pedro")));
		assertThat(client.getBusinessSite(), is(equalTo("Rural")));
	}

}
